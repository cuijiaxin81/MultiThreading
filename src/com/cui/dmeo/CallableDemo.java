package com.cui.dmeo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo implements Callable<String> {
    @Override
    public String call() throws Exception {
        int a = 1;
        int b = 2;
        System.out.println(a + b);
        return  "执行结果：" + (a + b);
    }

    public static void main(String[] args) throws Exception{
        //创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        CallableDemo callableDemo = new CallableDemo();
        Future<String> future = executorService.submit(callableDemo);
        String resulr = future.get(); //阻塞
        System.out.println(resulr);

    }
}
