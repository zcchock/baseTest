package com.zc.designpattern.lazysingleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author chock
 * @since 2020/2/20
 *
 * 考虑线程安全问题
 * 如果使用Double Check 进行优化，双重检查，防止重排序
 */
public class LazySingletonTest {

    private static Logger logger = LogManager.getLogger(LazySingletonTest.class);

    public void oneThread() {
        LazySingleton instance = LazySingleton.getInstance();
        LazySingleton instance1 = LazySingleton.getInstance();
    }

    public void moreThread() {

        new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(instance);
        }).start();

        new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(instance);
        }).start();

    }

    public static void main(String[] args) {
        new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            logger.info(instance);
        }).start();

        new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            logger.info(instance);
        }).start();
    }

}

/**
 * 单线程安全
 */
class LazySingleton {
    private volatile static LazySingleton lazySingleton;

    private LazySingleton() {
    }

    public synchronized static LazySingleton getInstance() {
        if (lazySingleton == null) {
            synchronized (LazySingleton.class) {
                if (lazySingleton == null) {
                    lazySingleton = new LazySingleton();
                    /*
                    字节码层面：
                    (底层的重排序可能会打乱 2 3两步操作，但多线程发生时，
                    第一个线程1-3-2顺序执行，执行完3的时候，已经引用赋值，
                    第二个线程在第一个判空的时候判断为非空，为了解决这个问题需要加volatile禁止重排序)
                    1.分配空间
                    2.初始化
                    3.引用赋值
                     */
                }
            }
        }
        return lazySingleton;
    }
}