package com.supcoder.aspectj;


import android.os.SystemClock;

import com.supcoder.library.annotation.CalculateTime;

import java.io.Serializable;


/**
 * Created by ${huozhenpeng} on 17/2/23.
 * Company : www.miduo.com
 * 演示AOP对构造方法的使用
 */

public class Person implements Serializable {


    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @CalculateTime(value = "1", type =1)
    public void setAge(int age) {
        this.age = age;
        SystemClock.sleep(100);
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        SystemClock.sleep(1000);
    }
    @CalculateTime(value = "1", type =1)
    public Person() {

        SystemClock.sleep(1500);
    }
}

