package com.rakib.java_concurrency;

import com.rakib.java_concurrency.utils.CommonUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class HelloWorldService {
    public CompletableFuture<String> printHello(){
        CommonUtils.delay(100);
        return CompletableFuture.supplyAsync(() -> "Hello");
    }
    public CompletableFuture<String> printWorld(){
        CommonUtils.delay(100);
        return CompletableFuture.supplyAsync(() -> "World");
    }
    public CompletableFuture<String> printName(String name){
        CommonUtils.delay(100);
        return CompletableFuture.supplyAsync(() -> name);
    }
}
