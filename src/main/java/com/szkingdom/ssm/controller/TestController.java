package com.szkingdom.ssm.controller;

import java.util.*;

/**
 * Created by tianf on 2016/11/28.
 */
public class TestController {

    HashMap hashMap = new HashMap();

    public static void main(String[] args) {
        byte a = 127;
        byte b = 127;
        //b = a + b; // error : cannot convert from int to byte
        b += a; // ok

        System.out.println(0.1 * 3);
        if(0.1 * 3 == 0.3) {
            System.out.println(true);

        }
        //System.out.println();

        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024/1024.0);               //最大内存的字节数
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);            //总内存的字节数
        System.out.println(Runtime.getRuntime().freeMemory()/1024/1024);            //返回剩余空间的字节数

        //1G = 1024M  = 1024kb  =  2014B

        PriorityQueue pq = new PriorityQueue();

        //Comparator   接口用于定义对象的自然顺序
        //comparator 通常用于定义用户定制的顺序。
        //Comparator comparator

        //mySort();
        //arraysTest();

        removeTest();
    }


    public static List mySort() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(8);
        list.add(5);
        list.add(4);
        Collections.sort(list);

        for(Integer i : list) {
            System.out.println(i);
        }
        return list;
    }

    public static void arraysTest() {
        int[] a = {2,7,4,8,1};
        //Arrays.asList();
        Arrays.sort(a);
        for(int i = 0 ;i< a.length; i++) {
            System.out.println(a[i]);
        }
       // Arrays.toString(a);
    }

    public static void removeTest() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(8);
        list.add(5);
        list.add(4);

        Iterator<Integer> itr = list.iterator();
        while(itr.hasNext()) {
            System.out.println(itr.next());
            /*if(itr.next() == 8) {
                itr.remove();
            }*/
        }
    }
}
