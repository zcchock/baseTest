package com.zc.designpattern.innerclasssingleton;

/**
 * @author chock
 * @since 2020/2/21
 */
public class InnerClassSingletonTest {


    public static void main(String[] args) {

        new Thread(() -> {
            InnerClassSingleton instance = InnerClassSingleton.getInstance();
            System.out.println(instance);
        }).start();
        new Thread(() -> {
            InnerClassSingleton instance = InnerClassSingleton.getInstance();
            System.out.println(instance);
        }).start();
    }
}


class InnerClassSingleton {
    private static class InnerClassHolder {
        private static InnerClassSingleton innerClassSingleton = new InnerClassSingleton();
    }

    private InnerClassSingleton() {
    }
    /*
    只有调用该方法，访问了静态类的静态属性：innerClassSingleton。触发初始化
    初始化过程已经完成实例，保证唯一性
     */
    public static InnerClassSingleton getInstance() {
        return InnerClassHolder.innerClassSingleton;
    }
}