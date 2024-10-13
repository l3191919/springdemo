package com.lyz.mianshi.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * iterator 使用
 */
public class IteratorService {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("q");
        list.add("w");
        list.add("e");
        list.add("r");

        Iterator iterator =  list.iterator();
        while (iterator.hasNext()){
            String s =  String.valueOf(iterator.next()) ;
            if (s.equals("w")){
                iterator.remove();
            }
        }
        System.out.println(list);
    }
}
