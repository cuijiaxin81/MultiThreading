package com.cui.dmeo;

import java.util.concurrent.TimeUnit;

public class ThreadStatus {

    public static void main(String[] args) {
        //TIME_WAITTING
        new Thread(()->{
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"timewaiting").start();

        //WAITING,线程在ThreadStatus类锁上等待
        new Thread(()->{
            while (true) {
                synchronized (ThreadStatus.class) {
                    try {
                        ThreadStatus.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Waiting").start();
        //线程在ThreadStatus加锁后，不会释放锁
        new Thread(new BlockedDemo(),"BlockedDemo-1").start();
        new Thread(new BlockedDemo(),"BlockedDemo-2").start();
    }
    static class BlockedDemo extends Thread {
        @Override
        public void run() {
            synchronized (BlockedDemo.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
