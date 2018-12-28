package com.cui.dmeo.zookeeper;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        int N = 9;
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < N; i++) {
            new Woker(i,semaphore).start();
        }
    }

    static class Woker extends Thread{
        private int num;
        private Semaphore semaphore;
        public Woker(int num,Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人"+this.num+"占用一个机器在生产.....");
                Thread.sleep(1000);
                System.out.println("工人"+this.num+"释放出机器");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
