package com.bianxianmao.demo.test;

import org.springframework.stereotype.Controller;

/**
 * @author sunzs
 * created on 2018/8/31
 */
@Controller
public class ThreadTest1 {


    public static void main(String[] args) {
        ThreadDemo t1 =  new ThreadDemo("thread-1");
        t1.start();

        ThreadDemo t2 = new ThreadDemo("thread-2");
        t2.start();
    }
}
