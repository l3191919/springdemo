package com.lyz.alibaba.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

@Component
public class KafkaProducer {
    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;
    /**
     * 发送消息
     */
    public void sendMessage() {
        try{
            //生产消息
            String message = "hello ！ 测试kafka ";
            ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send("hello","hello-kafka", message);
            listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    System.out.println("sendMessage success");
                }
                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("sendMessage error");
                }
            });
        }catch (Exception e){
            System.out.println("sendMessage exception");
        }

    }
}
