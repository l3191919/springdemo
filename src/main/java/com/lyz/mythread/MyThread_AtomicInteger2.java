package com.lyz.mythread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThread_AtomicInteger2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //创建一个线程池服务 线程池中线程的数量是4
        ExecutorService executor = Executors.newFixedThreadPool(4);
        //
        AtomicInteger count = new AtomicInteger(0);

        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            Future<?> future = executor.submit(() -> {
                // 假设每个任务都增加count的值
                count.compareAndSet(count.get(),count.incrementAndGet());
                System.out.println("线程名:"+Thread.currentThread().getName()+","+count.get());
            });
            futures.add(future);
        }

        // 关闭executor，不再接受新任务，但已提交的任务将继续执行
        executor.shutdown();

        // 等待所有任务完成
        executor.awaitTermination(3, TimeUnit.SECONDS);

        // 现在可以安全地读取count的值
        System.out.println("Final count: " + count.get());
    }

}
