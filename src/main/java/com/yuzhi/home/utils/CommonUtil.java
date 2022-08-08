package com.yuzhi.home.utils;


public class CommonUtil {

    public static void main(String[] args) {
        new CommonUtil().test1();
    }

    public void test1() {
        String a = "1";
        String intern = a.intern();
        System.out.println(a == intern);

        String b = "1";
        System.out.println(a == b);



        System.out.println("1");
    }

}
