package com.lyz.service.impl;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.*;

@Slf4j
public class MyListServiceImpl {

    public static void main(String[] args) {
        MyListServiceImpl my = new MyListServiceImpl();
        my.myListfor();
    }
    public void myListType(){
        String[] strings = {"1","2","3"};
        ArrayList<String> arrayList = (ArrayList<String>) Arrays.asList("1","2","3");
        LinkedList<String> linkedList = (LinkedList<String>) Arrays.asList("1","2","3");
        Vector<String> vector = (Vector<String>) Arrays.asList("1","2","3");

        Collections.synchronizedList(arrayList);


    }





    public  void myListfor(){
        List<String> list = new ArrayList<>();
        list.add("一");
        list.add("二");
        list.add("三");
        list.add("四");
        list.add("五");
        list.add("六");
        list.add("七");

        for (String l1:list){
            log.info(l1);
        }
        //或者
        Iterator<String> iterable= list.iterator();
//        while (iterable.hasNext()){
//            log.info(iterable.next());
//        }
        //如果需要进行删除元素就直接使用Iterator迭代

        int i = 0;
        while(iterable.hasNext()){
            //i++ 先赋值 再计算
            String string = iterable.next();
            if(i%2!=0){
                log.info("remove:"+string);
                iterable.remove();
            }
            i++;
        }
        //使用for循环
        for (String s:list){
            log.info(s);
        }
    }

}
