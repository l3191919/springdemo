package com.lyz.mythread;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread_AtomicInteger1 {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger I = new AtomicInteger(0);

        for (int i =0;i<10;i++){

            new Thread(()->{
                System.out.println("线程名"+Thread.currentThread().getName());
                for (int j =0;j <10;j ++){
                    I.compareAndSet(I.get(),I.incrementAndGet());

//                    if(j%2==0){
//                        //添加1
//                        I.compareAndSet(I.get(),I.incrementAndGet());
//                    }else{
//                        //减去1
//                        //I.compareAndSet(I.get(),I.decrementAndGet());
//
//                    }
                }

            }).start();

        }
        /**
         * 如果不加 Thread.sleep 主线程循环开启后直接读取.
         * Thread.sleep(1000);
         * 线程名Thread-0
         * 线程名Thread-1
         * 0
         * 线程名Thread-2
         * 线程名Thread-3
         * 线程名Thread-4
         * 线程名Thread-6
         * 线程名Thread-7
         * 线程名Thread-5
         * 线程名Thread-8
         * 线程名Thread-9
         * 是这样的就像是 I.compareAndSet() 还没开始执行
         *
         * 加上以后就正常了
         *
         * MyThread_AtomicInteger2 使用ExecutorService和Future 可以更优雅的等到任务完成
         */
        Thread.sleep(1000);

        System.out.println(I.get());
    }

}
