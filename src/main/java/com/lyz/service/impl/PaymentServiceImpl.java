package com.lyz.service.impl;




import com.alibaba.fastjson.JSON;
import com.lyz.alibaba.invest.InvestChainPatternService;
import com.lyz.alibaba.kafka.KafkaProducer;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyz.dao.PaymentDao;
import com.lyz.entities.Payment;
import com.lyz.entities.RedisCityData;
import com.lyz.entities.UserData;
import com.lyz.service.PaymentService;
import com.lyz.service.ThreadPoolService;
import com.lyz.util.ObjectToMapConverterUtil;
import com.lyz.util.RedisService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("paymentService")
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    RedisService redisService;

    @Resource
    PaymentDao paymentDao;
    @Autowired
    InvestChainPatternService investChainPatternService;
    @Autowired
    KafkaProducer kafkaProducer;
    @Autowired
    ThreadPoolService threadPoolService;



    @Override
    public int create(Payment payment){
        return paymentDao.create(payment);
    }
    @Override
    public  Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }
    @Override
    public  Payment getThreadPaymentById(Long id){
        //多线程
        return threadPoolService.getPaymentData(id);

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

    @Override
    public void sendMessage() {
        kafkaProducer.sendMessage();
    }

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



    public static void main(String[] args) {
        InvestChainPatternService i = new InvestChainPatternService();
        i.go("EWO",123456L,"IMPORT");
    }






}
