package com.lyz.service.impl;

import com.lyz.util.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
@Slf4j
@Service("myCompletableFuture")
public class MyCompletableFuture {
    public static void main(String[] args) {
        MyCompletableFuture my = new MyCompletableFuture();
        my.AsyncDemo04();
    }

    //使用指定的线程池执行异步代码。此异步方法无法返回值。
    public void AsyncDemo01(){
        log.info("现在的线程为"+Thread.currentThread().getName());

        ThreadPoolExecutor executor = ThreadPoolUtils.getThreadPoolExecutor();

        CompletableFuture.runAsync(()->{
            log.info("异步线程为"+Thread.currentThread().getName());
            log.info("111");

        },executor);

    }
    //使用指定的线程池执行异步代码。此异步方法有返回值。
    public void AsyncDemo02(){
        log.info("现在的线程为"+Thread.currentThread().getName());

        ThreadPoolExecutor executor = ThreadPoolUtils.getThreadPoolExecutor();

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            log.info("异步线程为"+Thread.currentThread().getName());
            log.info("111");
            return "aaaaaaaaaaa";
        },executor);
        //获取异步线程执行结果
        log.info("获取异步线程执行结果"+completableFuture.join());
    }

    //第一个线程 A任务返回参数后才可以调用B
    //第二个线程 C任务则是不和AB任务相关只是最后需要统一进行入参
    //D任务接受 AB任务的返回 和 C任务的返回
    public void AsyncDemo03(){

        log.info("现在的线程为"+Thread.currentThread().getName());
        /**
         * thenApply：该方法会在当前 CompletableFuture 完成后的同一个线程中执行提供的函数。
         * 也就是说，如果当前 CompletableFuture 是由某个线程池中的线程完成的，那么 thenApply 中的函数也会在该线程中执行。
         * thenApplyAsync：该方法会在默认的 ForkJoinPool.commonPool() 中异步执行提供的函数，
         * 或者如果你提供了一个自定义的 Executor，则会在该 Executor 中执行。这意味着，thenApplyAsync 会将任务提交给另一个线程，
         * 而不是在当前线程中执行。
         */
        ThreadPoolExecutor executor = ThreadPoolUtils.getThreadPoolExecutor();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            //A任务
            log.info("A任务现在的线程为"+Thread.currentThread().getName());
            return "abc";
        },executor).thenApply(e->{
            //B任务
            e = (e+"de").toUpperCase();
            log.info(e);
            log.info("B任务现在的线程为"+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            return e;
        });


        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(()->{

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("C任务现在的线程为"+Thread.currentThread().getName());
            return 100*11;
        },executor);

        String ab = completableFuture.join();
        Integer c = completableFuture1.join();


        log.info("AB任务是:"+ab+"C任务是:"+c);
    }


    public void AsyncDemo04() {
        log.info("现在的线程为" + Thread.currentThread().getName());

        ThreadPoolExecutor executor = ThreadPoolUtils.getThreadPoolExecutor();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            // A任务
            log.info("A任务现在的线程为" + Thread.currentThread().getName());
            return "abc";
        }, executor).thenApply(e -> {
            // 使用thenApply保持线程不变
            // B任务
            e = (e + "de").toUpperCase();
            log.info(e);
            log.info("B任务现在的线程为" + Thread.currentThread().getName());
            // 注意：通常不建议在thenApply中执行阻塞操作
            // 这里只是为了演示而保留
            try {
                Thread.sleep(1000); // 这可能会阻塞线程池中的线程，应谨慎使用
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            return e;
        }); // 注意这里没有传递executor给thenApply

        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("C任务现在的线程为" + Thread.currentThread().getName());
            return 100 * 11;
        }, executor);

        String ab = completableFuture.join();
        Integer c = completableFuture1.join();

        log.info("AB任务是:" + ab + " C任务是:" + c);
    }
}
