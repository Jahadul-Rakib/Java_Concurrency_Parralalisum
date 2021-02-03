package com.rakib.java_concurrency.service;

import com.rakib.java_concurrency.utils.CommonUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


public class GeneralService {

    public String getUser(){
        CommonUtils.delay(1000);
        return "Rakib";
    }
    public Integer getUserAge(){
        CommonUtils.delay(1000);
        return 35;
    }
    public String getUserCountry(){
        CommonUtils.delay(1000);
        return "Bangladesh";
    }
    public CompletableFuture<String> getFullName(String fName, String lName){
        return CompletableFuture.supplyAsync(() -> {
            CommonUtils.delay(1000);
            return fName+" "+lName;
        });
    }
    public CompletableFuture<String> getFullNameNonBlocking(String fName, String lName){
        return CompletableFuture.supplyAsync(() -> fName+" "+lName);
    }
}
