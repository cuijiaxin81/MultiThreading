package com.cui.dmeo;

public class Demo {
    public static void main(String[] args) {
        Object object = new Object();
        ThreadNotify notify = new ThreadNotify(object);
        ThreadWaitDemo wait = new ThreadWaitDemo(object);

        wait.start();
        notify.start();
    }
}
