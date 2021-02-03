package com.rakib.java_concurrency;

import com.rakib.java_concurrency.utils.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class JavaConcurrencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaConcurrencyApplication.class, args);
        HelloWorldService service = new HelloWorldService();

        CompletableFuture<CompletableFuture<String>> hello = CompletableFuture.supplyAsync(service::printHello)
                .handle((value, throwable) -> {
                    if (Objects.nonNull(throwable)) {
                        try {
                            throw new Exception(throwable.getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return value;
                });
        CompletableFuture<CompletableFuture<String>> world = CompletableFuture.supplyAsync(service::printWorld);
        CompletableFuture<CompletableFuture<String>> name = CompletableFuture.supplyAsync(() -> service.printName("Rakib"));

        String future =
                hello
                        .thenCombine(world, (x, y) -> x + " " + y)
                        .thenCombine(name, (x1, y1) -> x1 + " " + y1)
                        .handle((s, throwable) -> {
                            if (Objects.nonNull(throwable)) {
                                System.out.println(throwable.getMessage());
                            }
                            return s;
                        })
                        .thenApply(String::toUpperCase)
                        .join();
        System.out.println(future);

        String futureOtherWay =
                hello
                        .thenCombine(world, (x, y) -> x + " " + y)
                        .thenCombine(name, (x1, y1) -> x1 + " " + y1)
                        .exceptionally(throwable -> {
                            System.out.println(throwable.getMessage());
                            return "";
                        })
                        .thenApply(String::toUpperCase)
                        .join();
        System.out.println(futureOtherWay);


        String futureOtherWay2 =
                hello
                        .thenCombine(world, (x, y) -> x + " " + y)
                        .thenCombine(name, (x1, y1) -> x1 + " " + y1)
                        .whenComplete((s, throwable) -> {
                            if (Objects.nonNull(throwable)) {
                                System.out.println(throwable.getMessage());
                            }
                        })
                        .thenApply(String::toUpperCase)
                        .join();
        System.out.println(futureOtherWay2);


        CommonUtils.delay(5000);
    }


}
