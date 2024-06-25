package com.lyz.theradtask;

import com.alibaba.fastjson.JSON;
import com.lyz.entities.Payment;
import com.lyz.service.PaymentService;
import com.lyz.util.AppUtil;
import com.lyz.util.ThreadSwapParameter;
import com.lyz.util.error.NEException;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @author lyz
 * @date 2020/5/1119:38
 * 用户未读的推送消息数
 */
@Slf4j
//@Component
public class PaymentTask implements Callable<Payment>{

    private PaymentService paymentService;
    private Long id;
    private ThreadSwapParameter parameter;
    public PaymentTask( PaymentService paymentService, Long id, ThreadSwapParameter parameter){
        this.parameter = parameter;
        this.paymentService = paymentService;
        this.id = id;
    }

    @Override
    public Payment call(){
        AppUtil.addSwapThreadLocalParameter(parameter);
        Payment payment = null;
        try{

            payment = paymentService.getPaymentById(id);
            log.info("用户的返回: {}", JSON.toJSONString(payment));
        }catch (NEException e) {
            log.info("用户未读的推送消息数的返回错误: {}", e.getMessage());
        }finally{
        AppUtil.clearSwapThreadLocalParameter();
        }
        return payment;
    }

}
