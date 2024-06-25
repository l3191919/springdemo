package com.lyz.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
/**
 * 核心线程数（Core Pool Size）：
 *
 * 核心线程数是线程池的基本大小，它表示即使线程处于空闲状态也不会被销毁的线程数。
 * 这个值应该基于你的硬件资源（特别是CPU核心数）和任务性质来设置。通常，如果你的任务是CPU密集型的，核心线程数可以设置为CPU核心数；如果是IO密集型的，可以适当增加核心线程数以利用多核CPU的优势。
 * 最大线程数（Maximum Pool Size）：
 *
 * 最大线程数表示线程池允许的最大线程数。当工作队列满了，并且已创建的线程数小于最大线程数时，线程池会再创建新的线程来执行任务。
 * 通常，最大线程数可以设置为核心线程数的几倍，具体取决于你的系统资源和任务性质。如果任务执行时间较短且频繁，可以适当增加最大线程数以提高吞吐量。
 * 工作队列（Work Queue）：
 *
 * 工作队列用于保存待执行的任务。选择合适的队列类型和大小对线程池的性能至关重要。
 * 常见的队列类型有 ArrayBlockingQueue（有界队列）和 LinkedBlockingQueue（无界队列）。
 * 如果你的任务量相对稳定，可以选择有界队列来避免资源耗尽；如果任务量可能非常大，可以选择无界队列，但要注意可能导致的内存溢出问题。
 * 线程存活时间（Keep Alive Time）：
 *
 * 当线程数量大于核心线程数时，这是多余空闲线程在终止前等待新任务的最长时间。
 * 这个值可以根据你的需求进行配置。如果你希望线程池中的线程尽快释放资源，可以将存活时间设置得短一些；如果你希望保持一定数量的空闲线程以应对突发任务，可以将存活时间设置得长一些。
 * 时间单位（Time Unit）：
 *
 * 时间单位用于指定线程存活时间的单位，如 TimeUnit.SECONDS 表示秒。
 * 根据你的需求选择合适的时间单位。
 * 线程工厂（Thread Factory）：
 *
 * 线程工厂用于创建新线程。你可以使用默认的线程工厂，也可以自定义线程工厂来设置线程的名称、优先级等属性。
 * 通常情况下，如果你没有特别的需求，可以使用默认的线程工厂。
 * 拒绝策略（Rejected Execution Handler）：
 *
 * 当工作队列和线程池都满了，新提交的任务会被拒绝。选择合适的拒绝策略可以避免程序崩溃或性能下降。
 * 常见的拒绝策略有 AbortPolicy（直接抛出异常）、CallerRunsPolicy（调用执行任务的 run 方法）、DiscardOldestPolicy（丢弃队列中最老的任务）和 DiscardPolicy（直接丢弃新任务）。
 * 根据你的需求选择合适的拒绝策略。如果你希望任务不被拒绝，可以选择 CallerRunsPolicy 或 DiscardOldestPolicy；如果你希望程序在任务被拒绝时抛出异常，可以选择 AbortPolicy；
 * 如果你希望直接丢弃新任务，可以选择 DiscardPolicy。
 */
public class ThreadPoolUtils {

    public final static ThreadPoolExecutor threadPoolExecutor;


    static {
        int processors = 64;
        int corePoolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;
        log.info("corePoolSize:{}", corePoolSize);
        corePoolSize = corePoolSize > processors ? corePoolSize : processors;
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, corePoolSize,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10000),
                (r, executor) -> {
                    try {
                        executor.getQueue().put(r);
                    } catch (InterruptedException e) {
                        log.warn("retry put task error,message:{}", e);
                    }
                });

    }


    public static ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }

}
