package com.cui.dmeo;

import java.util.concurrent.TimeUnit;

public class InterruptDemo1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while (true) {
                boolean i = Thread.currentThread().isInterrupted();
                if (i) {
                    System.out.println("before:" + i);
                    //对线程进行复位，中断标志为false
                    Thread.interrupted();
                    System.out.println("after:" + Thread.currentThread().isInterrupted());
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt(); //设置终端标识，中断表示为true
    }
}
