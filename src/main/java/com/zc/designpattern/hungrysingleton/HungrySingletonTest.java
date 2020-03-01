package com.zc.designpattern.hungrysingleton;

/**
 * @author chock
 * @since 2020/2/21
 */
public class HungrySingletonTest {

    public static void main(String[] args) {


        HungrySingleton instance = HungrySingleton.getInstance();
        HungrySingleton instance2 = HungrySingleton.getInstance();
        System.out.println(instance == instance2);
    }

}

class HungrySingleton {

    private static HungrySingleton hungrySingleton = new HungrySingleton();

    public HungrySingleton() {
    }

    /*
    加载 -> 连接（验证，准备，解析） -> 初始化：给静态变量赋值
    所以在初始化阶段就完成了hungrySingleton的实例化
     */
    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }
}