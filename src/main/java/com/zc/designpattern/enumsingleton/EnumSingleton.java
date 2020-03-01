package com.zc.designpattern.enumsingleton;


/**
 * @author chock
 * @since 2020/2/21
 */
public enum EnumSingleton {
    INSTANCE;
}


class EnumTest {

    public static void main(String[] args) {
        EnumSingleton instance = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = EnumSingleton.INSTANCE;
        System.out.println(instance == instance2);
    }
}