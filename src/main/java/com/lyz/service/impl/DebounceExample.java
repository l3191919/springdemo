package com.lyz.service.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class DebounceExample {
    private static final int DEBOUNCE_TIME = 1000; // 防抖时间为1000毫秒
    private AtomicReference<Future<?>> futureRef = new AtomicReference<>();

    public void debouncedOperation(Runnable operation) {
        Future<?> prev = futureRef.getAndSet(Executors.newSingleThreadScheduledExecutor().schedule(operation, DEBOUNCE_TIME, TimeUnit.MILLISECONDS));
        if (prev != null) {
            prev.cancel(false);
        }
    }

    public static void main(String[] args) {
        DebounceExample debounceExample = new DebounceExample();
        Runnable action = () -> System.out.println("Action performed");

        // 测试防抖效果
        for (int i = 0; i < 10; i++) {
            debounceExample.debouncedOperation(action);
        }
    }
}