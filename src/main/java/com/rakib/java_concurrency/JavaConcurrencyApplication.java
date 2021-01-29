package com.rakib.java_concurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaConcurrencyApplication {

    private static String result = "";

    private static void hello() {
        result = result.concat("Hello");
    }

    private static void world() {
        result = result.concat(" World");
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(JavaConcurrencyApplication.class, args);

        Thread helloThread = new Thread(() -> hello());
        helloThread.start();
        Thread wordThread = new Thread(() -> world());
        wordThread.start();

        helloThread.join();
        wordThread.join();

        System.out.println("Result is " + result);


    }

}
