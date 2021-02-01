package com.rakib.java_concurrency.utils;

import org.apache.commons.lang3.time.StopWatch;

public class CommonUtils {
    public static StopWatch stopWatch = new StopWatch();
    public static void delay(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
