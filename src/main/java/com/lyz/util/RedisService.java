package com.lyz.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String FREQUENCY = "bt-app-service:";

    public String getValue(String key) {
        Object value = redisTemplate.opsForValue().get(FREQUENCY + key);
        if (StringUtils.isEmpty(value)) {
            return null;
        } else {
            return value.toString();
        }
    }

    /**
     * 获取hash的值
     *
     * @param key1
     * @param key2
     * @return
     * @remark 车辆中心专用，直接获取车辆中心插入redis的值
     */
    public String getVehicleHashValue(String key1, String key2) {
        Object value = redisTemplate.opsForHash().get(key1, key2);
        if (StringUtils.isEmpty(value)) {
            return null;
        } else {
            return value.toString();
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean putHashKey(String key, String item, Object value) {
        return putHashKey(key, item, value, 0L);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean putHashKey(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(FREQUENCY + key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(FREQUENCY + key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 指定缓存失效时间
     * @param key
     * @param time
     * @param timeUnit
     * @return
     */
    public boolean expire(String key, long time,TimeUnit timeUnit) {
        try {
            if (time > 0) {
                redisTemplate.expire(FREQUENCY + key, time,timeUnit);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 获取hash的值
     *
     * @param key1
     * @param key2
     * @return
     * @remark 聚合层自用
     */
    public String getHashValue(String key1, String key2) {
        Object value = redisTemplate.opsForHash().get(FREQUENCY + key1, key2);
        if (StringUtils.isEmpty(value)) {
            return null;
        } else {
            return value.toString();
        }
    }

    public Boolean putKey(String key, String value, Long time, TimeUnit unit) {
        if (StringUtils.isEmpty(time)) {
            redisTemplate.opsForValue().set(FREQUENCY + key, value);
        } else {
            redisTemplate.opsForValue().set(FREQUENCY + key, value, time, unit);
        }
        return true;
    }

    public Map<Object, Object> getHashValues(String key) {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(FREQUENCY + key);
        if (!entries.isEmpty()) {
            return entries;
        }
        return new HashMap<>();
    }

    public boolean putHash(String key, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(FREQUENCY + key, map);
        return true;
    }

    public void removeHash(String key, String item) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(item)) {
            redisTemplate.opsForHash().delete(FREQUENCY + key, item);
        }
    }

    public boolean setHashTime(String key, Long time, TimeUnit unit) {
        redisTemplate.expire(FREQUENCY + key, time, unit);
        return true;
    }

    public boolean deleteKey(String key) {
        redisTemplate.delete(FREQUENCY + key);
        return true;
    }

    public boolean deleteOneOfSet(String key,String terminal) {
        redisTemplate.opsForSet().remove(key, terminal);
        return true;
    }



    public boolean existedKey(String key) {
        Boolean flag = redisTemplate.hasKey(FREQUENCY + key);
        return flag;
    }

    //插入分布式锁
    public Boolean addLock(String key, String value, Long lockTime, TimeUnit unit) {
        return redisTemplate.opsForValue().setIfAbsent(FREQUENCY + key, value, lockTime, unit);

    }

    //获取key的生命周期
    public Long getExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(FREQUENCY + key, timeUnit);
    }


    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true
     */
    public boolean existedHashKey(String key, String item) {
        Boolean flag = redisTemplate.opsForHash().hasKey(FREQUENCY + key, item);
        return flag;
    }

    /**
     * 对某个键的值自增
     * @author liboyi
     * @param key 键
     * @param liveTime 超时时间，0为不超时
     * @return
     */
    public Long incr(String key, long liveTime) {
        return redisTemplate.opsForValue().increment(key, 1);
    }


    /**
     * 添加地理位置
     */
    public void addGeoLocation(String key,Double longitude,Double latitude,String member){
        redisTemplate.opsForGeo().add(key,new Point(longitude,latitude),member);
    }

    /**
     * 测定两个位置的距离
     * @return
     */

    public Double distance (String key, String member1, String member2){

        Distance distance = redisTemplate.opsForGeo().distance(key,member1,member2,Metrics.KILOMETERS);

        return  distance.getValue();
    }




}
