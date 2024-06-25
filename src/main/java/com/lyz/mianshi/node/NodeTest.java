package com.lyz.mianshi.node;

import java.util.ArrayList;
import java.util.List;

public class NodeTest {
    public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("q");
    list.add("w");
    list.add("e");
    list.add("r");

        Node node12 = zhuanhuan(list,new Node(list.get(0)));
        while (node12.getNext()!=null) {
            System.out.println(node12.getValue());
        }

//       for(int i=0;5>i;i++){
//           Node node = new Node(i);
//           node
//       }
    }

    //需要node new新的
        public static Node zhuanhuan(List<String> list,Node node){
        //node.setValue(list.get(0));
        for(int k = 1;list.size()>k;k++){
            //找到
            //Node endNode = nodeF(node);
//            int j = k + 1;
//            if (list.size() > j) {
//                endNode.setNext(new Node(list.get(j)));
//                }
            Node node2 = node;
            while (node2.getNext()==null) {
                node2 = node2.getNext();
                Node node1 = new Node(list.get(k));
                int j = k + 1;
                if (list.size() > j) {
                    node1.setNext(new Node(list.get(j)));
                }
            }

        }
        return node;
        }

    public static Node nodeF(Node node){
        if(node.getNext()==null){
            return node;
        }else{
            nodeF(node.getNext());
        }
        return null;
    }

    //加入next
//    public static void addHead(Node node){
//        if(null==node.getNext()){
//            node.set
//        }
//    }




}
