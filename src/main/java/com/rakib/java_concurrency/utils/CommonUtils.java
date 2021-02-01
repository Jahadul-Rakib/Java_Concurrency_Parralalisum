package com.rakib.java_concurrency.utils;

import org.apache.commons.lang3.time.StopWatch;

public class CommonUtils {
    public static StopWatch stopWatch = new StopWatch();

    public static void startTimer() {
        stopWatch.start();
    }

    public static void takeTime() {
        stopWatch.stop();
        System.out.println("Total Time Taken:    "+stopWatch.getTime());
        stopWatch.reset();
    }

    public static void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
