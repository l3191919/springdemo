package com.lyz.service.impl;




import com.alibaba.fastjson.JSON;
import com.lyz.alibaba.pattern.zerenlian.InvestChainPatternService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyz.dao.PaymentDao;
import com.lyz.dao.UserDao;
import com.lyz.entities.*;
import com.lyz.service.PaymentService;
import com.lyz.service.ThreadPoolService;

import com.lyz.util.ObjectToMapConverterUtil;
import com.lyz.util.RedisService;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("paymentService")
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    RedisService redisService;

    @Resource
    PaymentDao paymentDao;
    @Resource
    UserDao userDao;

    @Autowired
    InvestChainPatternService investChainPatternService;
//    @Autowired
//    KafkaProducer kafkaProducer;
    //@Autowired
    //ThreadPoolService threadPoolService;


    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    @Override
    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    @Override
    public int insertPayment(Payment payment){
        return paymentDao.insertPayment(payment);
    }

    @Override
    public  Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }
    @Override
    public  Payment getThreadPaymentById(Long id){
        //多线程
        //threadPoolService.getPaymentData(id); 循环依赖了 修改成下面的方法
        return     ((ThreadPoolService) AopContext.currentProxy()).getPaymentData(id);

    }

    @Override
    public IPage<Payment> getPaymentByCompanyId (Long companyId){
        IPage<Payment> page = paymentDao.getPaymentByCompanyId(new Page<>(1,5),companyId);
        System.out.println("总页数： " + page.getPages());
        System.out.println("总记录数： " + page.getTotal());
        page.getRecords().forEach(System.out::println);

        return page;
    }

    @Override
    public IPage<Payment> getPaymentByOr (HashMap<String,String> map){
        IPage<Payment> page = paymentDao.getPaymentByOr(new Page<>(1,5),map);
        System.out.println("总页数： " + page.getPages());
        System.out.println("总记录数： " + page.getTotal());
        page.getRecords().forEach(System.out::println);

        return page;
    }
    @PostConstruct
    public void getMethod(){


        System.out.println("aaaaa");
    }

    @Override
    public void go() {
        investChainPatternService.go("EWO",123456L,"IMPORT");
    }

//    @Override
//    public void sendMessage() {
//        kafkaProducer.sendMessage();
//    }

    @Override
    public String setRedisData(UserData userData) {
        Map<String,String> data = new HashMap<>();
        try {
            data.putAll(ObjectToMapConverterUtil.toObjectMap(userData));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String key = JSON.toJSONString(System.currentTimeMillis());
        Boolean b = redisService.putHash(key,data);
        return key;
    }
    public String setRedisData1(Map<Object,Object> map) {


        Map<String,String> data = new HashMap<>();
//        try {
//            data.putAll(ObjectToMapConverterUtil.toObjectMap(userData));
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

        data.put("aa","aa");
        data.put("aa-a","aa-a");
        data.put("aa-b","aa-b");
        data.put("aa-c","aa-c");
        data.put("aa-d","aa-d");
        data.put("bb","bb");
        data.put("bb-a","bb-a");

        String key = JSON.toJSONString(System.currentTimeMillis());
        Boolean b = redisService.putHash(key,data);
        return key;
    }



    @Override
    public UserData getRedisData(String key) {
        Map<Object, Object> map = redisService.getHashValues(key);

        UserData userData = UserData.builder()
                .name(map.get("name").toString())
                .type(map.get("type").toString())
                .aa(map.get("aa").toString())
                .bb(map.get("bb").toString())
                .cc(map.get("cc").toString()).build();

        return userData;
    }






    /**
     * 修改相关redisHash中的内容
     * 并且从新查询一遍
     * @param key
     * @return
     */
    @Override
    public Map <Object,Object> getRedisData1(String Rediskey,String HashKey) {
        Map<Object, Object> map = redisService.getHashValues(Rediskey);

        //String aa="aa";
        Map <String,String> map1 = new HashMap<>();
        for(Map.Entry<Object,Object> e:map.entrySet()){
            //需要模糊筛选的key 不符合的不添加进去
            if(!HashKey.contains(String.valueOf(e.getKey()))){
                map1.put(String.valueOf(e.getKey()),String.valueOf(e.getValue()));

            }
        }

        redisService.putHash(Rediskey,map1);

        //取出看看效果
        return redisService.getHashValues(Rediskey);
    }


    @Override
    public String redisLength(RedisCityData originCity, RedisCityData targetCity){
        //写入redis中的数据
        redisService.addGeoLocation("cities",originCity.getLongitude(),originCity.getLatitude(),originCity.getCityName());
        redisService.addGeoLocation("cities",targetCity.getLongitude(),targetCity.getLatitude(),targetCity.getCityName());
        //根据写入的数据进行调用 并且得出数值
        Double distance = redisService.distance("cities",originCity.getCityName(),targetCity.getCityName());
        return distance.toString();
    }


    @Override
    public String redisLengthTest(){
        RedisCityData originCity =  RedisCityData.builder().cityName("北京").latitude(39.916527).longitude(116.397128).build();

        RedisCityData targetCity = RedisCityData.builder().cityName("洛阳").latitude(34.70431).longitude(112.51078).build();;


        //写入redis中的数据
        redisService.addGeoLocation("cities",originCity.getLongitude(),originCity.getLatitude(),originCity.getCityName());
        redisService.addGeoLocation("cities",targetCity.getLongitude(),targetCity.getLatitude(),targetCity.getCityName());
        //根据写入的数据进行调用 并且得出数值
        Double distance = redisService.distance("cities",originCity.getCityName(),targetCity.getCityName());
        log.info("距离是:{}",distance);

        return distance.toString();
    }

    /**
     * 批量写入数据 方法1
     * @param users
     */
    public void insertUsersBatch(List<Payment> users) {
        try (SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false)) {
            PaymentDao mapper = session.getMapper(PaymentDao.class);
            for (Payment user : users) {
                mapper.insertBatch(Collections.singletonList(user)); // 注意：这里实际上并不高效，只是为了演示
                // 注意：在实际应用中，你应该将整个用户列表传递给mapper的单个方法，如上XML配置所示
            }
            session.commit(); // 提交事务
        }
    }
    /**
     * 批量写入数据 方法2
     * @param users
     */
    // 更高效的做法是直接传递整个列表给mapper
    public void insertUsersBatchEfficiently(List<Payment> users) {
        try (SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false)) {
            PaymentDao mapper= session.getMapper(PaymentDao.class);
            mapper.insertBatch(users); // 假设insertBatch方法已经修改为接受List<User>
            session.commit(); // 提交事务
        }
    }



    public static void main(String[] args) {
        InvestChainPatternService i = new InvestChainPatternService();
        i.go("EWO",123456L,"IMPORT");
    }


    public List<Payment> selectById(Long id){
        List<Payment> payments = paymentDao.selectById(id);
        return payments;
    }

    public PaymentItem selectPaymentItemById(Long id){
        PaymentItem paymentItem= paymentDao.selectPaymentItemById(id);
        return paymentItem;
    }

    public UserItem selectUserItemById(Long id){
        UserItem paymentItem= userDao.selectUserItemById(id);
        return paymentItem;
    }

}
