package com.rakib.java_concurrency;

import com.rakib.java_concurrency.utils.CommonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class JavaConcurrencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaConcurrencyApplication.class, args);
        List<String> list = List.of("Rakib", "Dilruba", "Tuli");

        System.out.println("----------Stream----------");
        CommonUtils.startTimer();
        List<String> streamMethod = useStreamMethod(list);
        System.out.println(streamMethod);
        CommonUtils.takeTime();

        System.out.println("-----------ParallelStream---------");
        CommonUtils.startTimer();
        List<String> parallelStreamMethod = useParallelStreamMethod(list);
        System.out.println(parallelStreamMethod);
        CommonUtils.takeTime();
    }

    public static List<String> useStreamMethod(List<String> stringList) {
        CommonUtils.delay(1000);
        return stringList.stream()
                .map(JavaConcurrencyApplication::convertString)
                .collect(Collectors.toList());
    }

    public static List<String> useParallelStreamMethod(List<String> stringList) {
        return stringList.parallelStream()
                .map(JavaConcurrencyApplication::convertString)
                .collect(Collectors.toList());
    }

    public static String convertString(String data) {
        CommonUtils.delay(500);
        return "****" + data.toUpperCase().concat("****")+"Total Length->"+ data.length();
    }
}
