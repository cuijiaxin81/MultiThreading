package com.cui.dmeo;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {

    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread  = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()) {
                i ++;
            }
            System.out.println("Num:" + i);
        },"interruptDemo");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
