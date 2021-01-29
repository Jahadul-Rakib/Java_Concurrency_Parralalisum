package com.rakib.java_concurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class JavaConcurrencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaConcurrencyApplication.class, args);

        List<String> nameList = List.of("Rakib", "Tariqual", "Forida", "Tuli", "Dilruba");
        List<String> stringList = nameList.parallelStream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(nameList);
        System.out.println("----------");
        System.out.println(stringList);
    }

}
