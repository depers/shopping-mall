package com.fmall.test;

import org.junit.Test;

/**
 * Created by 冯晓 on 2019/1/30.
 */


public class StaticTest {

    public StaticTest() {
        System.out.println("执行构造器方法");
    }

    static {
        System.out.println("执行静态语句块");
    }

    public static void main(String[] args) {
        StaticTest staticTest = new StaticTest();
        System.out.println("main");
    }
}
