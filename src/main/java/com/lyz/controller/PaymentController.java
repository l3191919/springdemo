package com.lyz.controller;

import com.alibaba.fastjson.JSON;

import com.lyz.entities.CommonResult;
import com.lyz.entities.Payment;
import com.lyz.entities.Person;
import com.lyz.service.PaymentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    String serverPost;

    @Autowired
    PaymentService paymentService;
    //@Resource
    //private DiscoveryClient discoveryClient;


    /**
     * 新增
     * postman http://localhost:8001/payment/create?serial=atguigu002
     *
     * @param payment
     * @return
     */
//    @PostMapping(value = "payment/create")
//    public CommonResult create(@RequestBody Payment payment) {
//        int result = paymentService.create(payment);
//        log.info("*****插入结果: " + result);
//        if (result > 0) {
//            return new CommonResult(200, "插入数据库成功,serverPort:" + serverPost,result);
//        }
//        return new CommonResult(444, "插入数据库失败", null);
//    }

    /**
     * 查询
     * http://localhost:8001/payment/get/31
     *
     * @param id
     * @return
     */
    @GetMapping(value = "payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果: " + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPost,payment);
        }
        return new CommonResult(444, "没有对应记录,查询ID:" + id, null);
    }
    @GetMapping(value = "payment/byCompany/{companyId}")
    public CommonResult getPaymentByCompanyId(@PathVariable Long companyId) {
        IPage<Payment> payment = paymentService.getPaymentByCompanyId(companyId);
        log.info("*****查询结果: " + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPost,payment);
        }
        return new CommonResult(444, "没有对应记录,查询ID:" + companyId, null);
    }

//    @PostMapping(value = "payment/getPaymentByOr")
//    public CommonResult getPaymentByOr(@RequestBody HashMap<String,String> map) {
//        IPage<Payment> payment = paymentService.getPaymentByOr(map);
//        log.info("*****查询结果: " + payment);
//        if (payment != null) {
//            return new CommonResult(200, "查询成功,serverPort:" + serverPost,payment);
//        }
//        return new CommonResult(444, "没有对应记录,查询ID:" + JSON.toJSONString(map), null);
//    }

//    @GetMapping("patment/discovery")
//    public Object getDiscoveryClient() {
//
//       List<String> service =  discoveryClient.getServices();
//       for (String s:service){
//           log.info("~~~~~~~微服务发现",s);
//       }
//        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
//        for (ServiceInstance serviceInstance:serviceInstances){
//            log.info("CLOUD-PAYMENT-SERVICE微服务下有几个实例:{},ServiceId:{},Host:{},Post:{},Uri:{}",serviceInstances.size(),serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort(),serviceInstance.getUri());
//        }
//
//        return this.discoveryClient;
//    }
    @GetMapping(value = "/payment/lb")
    public  String paymentLb(){
        return serverPost;
    }

    @GetMapping(value = "/payment/feign/timeOut")
    public  String timeOut() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return serverPost;
    }

//    @PostMapping(value = "payment/getPerson")
//    public  void  getPerson(@RequestBody Person person){
//        log.info("getPerson:{}",JSON.toJSONString(person));
//    }

    public void getMethod(){

        for(Field field:paymentService.getClass().getFields()){
            if(field.isAnnotationPresent(Autowired.class)){

                if(field.getName().equals("PaymentDao")){

                }

            }
        }

        //获得所有方法，并且判断有@PostConstruct注释的方法
        for(Method method:paymentService.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(PostConstruct.class)){
                if(method.getName().equals("getMethod")){}
                try {
                    method.invoke(paymentService,null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}


