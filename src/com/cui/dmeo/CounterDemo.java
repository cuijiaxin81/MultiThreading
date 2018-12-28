package com.cui.dmeo;




import sun.plugin.dom.css.Counter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通过锁和循环CAS方式实现原子操作
 */
public class CounterDemo {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {
        final CounterDemo cas = new CounterDemo();
        List<Thread> ts = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int j = 0;j < 100;j ++) {
            Thread t = new Thread(()->{
                for (int i = 0;i < 1000;i ++){
                    cas.count();
                    cas.safeCount();
                }
            });
            ts.add(t);
        }
        for (Thread t : ts) {
            t.start();
        }
        //等待所有线程执行完毕
        for (Thread t: ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(cas.i);
        System.out.println(cas.atomicInteger.get());
        System.out.println(System.currentTimeMillis() - start);

    }

    /** 使用CAS实现线程安全计数器 */
    private void safeCount() {
        for (;;) {
            int i = atomicInteger.get();
            boolean suc = atomicInteger.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }

    /**非线程安全计数器*/
    private void count() {
        i++;
    }

}
