package com.lyz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyz.entities.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PaymentService {
    public int create(Payment payment);
    public int insertPayment(Payment payment);
    public Payment getPaymentById(Long id);
    public  Payment getThreadPaymentById(Long id);
    public IPage<Payment> getPaymentByCompanyId(Long companyId);
    public IPage<Payment> getPaymentByOr(HashMap<String, String> map);
    public void getMethod();
    public void go();
    //public void sendMessage();
    public String  setRedisData(UserData userData);
    public String  setRedisData1(Map<Object,Object> map);
    public String redisLength(RedisCityData originCity, RedisCityData targetCity);
    public String redisLengthTest();
    public UserData getRedisData(String vlues);
    public Map <Object,Object> getRedisData1(String Rediskey,String HashKey);

    public List<Payment> selectById(Long id);

    public PaymentItem selectPaymentItemById(Long id);
    public UserItem selectUserItemById(Long id);
}
