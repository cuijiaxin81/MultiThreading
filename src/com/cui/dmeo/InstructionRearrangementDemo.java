package com.cui.dmeo;

public class InstructionRearrangementDemo {
    private static int x = 0,y = 0;
    private static int a = 0,b = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            a = 1;
            x = b;
        });

        Thread t2 = new Thread(()->{
            b = 1;
            y = a;
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("x=" + x + "---->y=" + y);



    }
}
