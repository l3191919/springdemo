package com.lyz.service.impl;


import com.lyz.service.PaymentService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class XxlJobServerImpl {
    @Autowired
    PaymentService paymentService;


    @XxlJob("xxljobRunTest")
    public ReturnT<String> xxljobRunTest() throws Exception{

       paymentService.redisLengthTest();

       return ReturnT.SUCCESS;
    }
}
