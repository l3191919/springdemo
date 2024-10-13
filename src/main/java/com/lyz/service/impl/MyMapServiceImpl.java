package com.lyz.service.impl;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * HashMap
 * 特点：基于哈希表实现，允许使用 null 值和 null 键。不保证映射的顺序。
 * 用途：适用于需要快速查找、插入和删除操作的场景。
 * LinkedHashMap
 * 特点：是 HashMap 的一个子类，维护了一个双向链表，可以保持插入顺序或访问顺序。
 * 用途：适用于需要保持元素插入顺序或访问顺序的场景。
 * TreeMap
 * 特点：基于红黑树实现，按键的自然顺序或提供的 Comparator 进行排序。不允许使用 null 键。
 * 用途：适用于需要按键排序的场景。
 * Hashtable
 * 特点：线程安全的 Map 实现，不允许使用 null 键和 null 值。
 * 用途：适用于多线程环境中需要线程安全的 Map 操作，但性能相对较低。
 * ConcurrentHashMap
 * 特点：线程安全的 Map 实现，基于分段锁机制，允许高并发访问。允许使用 null 值和 null 键（从 Java 8 开始）。
 * 用途：适用于高并发访问的场景，性能优于 Hashtable。
 * WeakHashMap
 * 特点：基于哈希表实现，键是弱引用，当键不再被其他对象引用时，键值对会被垃圾回收器回收。允许使用 null 值和 null 键。
 * 用途：适用于缓存等场景，当键不再被使用时，可以自动释放内存。
 * IdentityHashMap
 * 特点：基于哈希表实现，使用 == 而不是 equals() 方法来比较键。允许使用 null 值和 null 键。
 * 用途：适用于需要基于对象身份（而不是内容）进行键比较的场景。
 */
@Slf4j
public class MyMapServiceImpl {
    public static void main(String[] args) {
        MyMapServiceImpl myMapService = new MyMapServiceImpl();
        myMapService.myMapFor();
    }
    public void myMapType(){
        //初始容量
        Map<String,String> stringMap1 = new HashMap<>(8);
        //排序
        Map<String,String> stringMap2 = new TreeMap<>();

        Map<String,String> stringMap3 = new LinkedHashMap<>();

        Map<String,String> stringMap4 = new Hashtable<>();

        ConcurrentHashMap<String,String> stringMap5 = new ConcurrentHashMap();
        //线程安全
        Collections.synchronizedMap(stringMap1);
    }

    public void myMapFor(){

        Map<String,String> stringMap1 = new HashMap<>(4);
        stringMap1.put("刘","阿达是的123");
        stringMap1.put("段","阿达是的32");
        stringMap1.put("董","阿达是的65");
        stringMap1.put("张","asdasd1");

        Map<String,String> stringMap2 = new HashMap<>(4);
        stringMap1.put("钱","阿达是的123");
        stringMap1.put("段","阿达是的32");
        stringMap1.put("董","阿达是的65");



        for (Map.Entry<String,String> map:stringMap1.entrySet()){
            log.info("key: "+map.getKey()+" value: "+map.getValue());
        }
        //stringMap1是否存在李的key
        if(stringMap1.containsKey("李")){
            log.info(stringMap1.get("李"));
        }
        //找到相同的key
        for(Map.Entry<String,String> less:stringMap2.entrySet()){
            if(stringMap1.containsKey(less.getKey())){
                log.info("for循环找到一样的 key: "+less.getKey()+" value: "+stringMap1.get(less.getKey()));
            }else{
                log.info("for循环找到不一样的 key: "+less.getKey()+" value: "+stringMap1.get(less.getKey()));
            }
        }

    }

}
