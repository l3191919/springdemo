package com.lyz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyz.entities.Payment;
import com.lyz.entities.RedisCityData;
import com.lyz.entities.UserData;

import java.util.HashMap;

public interface PaymentService {
    public int create(Payment payment);

    public  Payment getPaymentById(Long id);
    public  Payment getThreadPaymentById(Long id);
    public IPage<Payment> getPaymentByCompanyId(Long companyId);
    public IPage<Payment> getPaymentByOr(HashMap<String, String> map);
    public void getMethod();
    public void go();
    public void sendMessage();
    public String  setRedisData(UserData userData);
    public String redisLength(RedisCityData originCity, RedisCityData targetCity);
    public String redisLengthTest();
    public UserData getRedisData(String vlues);
}
