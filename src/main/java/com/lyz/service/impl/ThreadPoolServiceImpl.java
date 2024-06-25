package com.lyz.service.impl;


import com.lyz.entities.Payment;
import com.lyz.service.PaymentService;
import com.lyz.service.ThreadPoolService;
import com.lyz.theradtask.PaymentTask;
import com.lyz.util.AppUtil;
import com.lyz.util.ThreadPoolUtils;
import com.lyz.util.ThreadSwapParameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;

@Service
@Slf4j
public class ThreadPoolServiceImpl implements ThreadPoolService {
    @Autowired
    PaymentService paymentService;


    public Payment getPaymentData(Long id) {
        CompletionService<Payment> cs = new ExecutorCompletionService<>(ThreadPoolUtils.getThreadPoolExecutor());
        ThreadSwapParameter communityThreadSwapParameter = AppUtil.getCommunityThreadSwapParameter();
        Payment payment = null;

        Future<Payment> task = cs.submit(new PaymentTask(paymentService,id,communityThreadSwapParameter));


        try {
            payment = task.get();
            return payment;
        } catch (InterruptedException e) {
            //因为某些原因线程中断 中断后保证程序继续往下运行
            log.info("因为某些原因线程中断:{}",e.getMessage());
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
            log.info("因为某些原因程序错误:{}",e.getMessage());
        }
        return payment;
    }
}
