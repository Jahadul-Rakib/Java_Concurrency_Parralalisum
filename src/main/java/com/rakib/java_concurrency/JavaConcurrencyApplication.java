package com.rakib.java_concurrency;

import com.rakib.java_concurrency.service.GeneralService;
import com.rakib.java_concurrency.utils.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class JavaConcurrencyApplication {
    static GeneralService generalService = new GeneralService();

    public static void main(String[] args) {
        SpringApplication.run(JavaConcurrencyApplication.class, args);

        String s = completableFutureWithOneMethod();
        System.out.println(s);

        String s1 = completableFutureWithTwoMethod();
        System.out.println(s1);

        String s2 = completableFutureWithMultipleMethod();
        System.out.println(s2);

        CompletableFuture<String> s3 = whenFunctionCallCompleTableFun();
        System.out.println(s3);

        CompletableFuture<String> future = whenFunctionCallCompleTableFutureNonBlocking();
        System.out.println(future);

        CommonUtils.delay(150000);
    }


    /*----using completableFuture by blocking fashion-----*/
    public static String completableFutureWithOneMethod() {
        return CompletableFuture.supplyAsync(() -> generalService.getUser())
                .thenApply(s -> s.toUpperCase()) //apply other method to result when the method return a value
                //.thenAccept(s -> System.out.println(s))//take the final result
                .join();                                        //for blocking main method use '.join()'
    }

    /*----using completableFuture by blocking fashion and join them as a single output-----*/
    public static String completableFutureWithTwoMethod() {
        CompletableFuture<String> countryName = CompletableFuture.supplyAsync(() -> generalService.getUserCountry())
                .thenApply(s -> s.toUpperCase());
        CompletableFuture<String> userName = CompletableFuture.supplyAsync(() -> generalService.getUser())
                .thenApply(s -> s.toUpperCase());

        return userName
                .thenCombine(countryName, (x, y) -> x + " " + y)
                .thenApply(s -> "****" + s + "****")
                .join();

    }

    /*----using completableFuture by blocking fashion and join them as a single output-----*/
    public static String completableFutureWithMultipleMethod() {
        CompletableFuture<String> countryName = CompletableFuture.supplyAsync(() -> generalService.getUserCountry())
                .thenApply(s -> s.toUpperCase());
        CompletableFuture<String> userName = CompletableFuture.supplyAsync(() -> generalService.getUser())
                .thenApply(s -> s.toUpperCase());
        CompletableFuture<Integer> userAge = CompletableFuture.supplyAsync(() -> generalService.getUserAge())
                .thenApply(s -> s + 1);
        CompletableFuture<String> finalMessage = CompletableFuture.supplyAsync(() -> {
            CommonUtils.delay(1000);
            return "From Completable Future";
        });

        return userName
                .thenCombine(countryName, (previous, later) -> previous + " " + later)
                .thenCombine(userAge, (previousResult, age) -> previousResult + " " + age)
                .thenCombine(finalMessage, (previousResultWithAge, message) -> previousResultWithAge + " " + message)
                .thenApply(s -> "****" + s + "****")
                .join();

    }

    public static CompletableFuture<String> whenFunctionCallCompleTableFun() {
        return CompletableFuture.supplyAsync(() -> generalService.getUser())
                .thenCompose((previous) -> generalService.getFullName(previous, "Hasan"))
                .thenApply(String::toUpperCase);
    }

    public static CompletableFuture<String> whenFunctionCallCompleTableFutureNonBlocking() {
        return CompletableFuture.supplyAsync(() -> generalService.getFullNameNonBlocking("Rakib", "Hasan"))
                .thenApply(val -> val.toString().toLowerCase(Locale.ROOT));
    }

}
