package com.xiongtao.asmdemo;

import org.junit.Test;

public class TestCode {
    void test(){
        long s = System.currentTimeMillis();

        long e = System.currentTimeMillis();

        System.out.println("execute" + (e - s) + "ms.");

    }
}
