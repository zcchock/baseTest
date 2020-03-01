package com.zc.test.otherTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author chock
 * @since 2020/2/22
 */
public class TestMap {


    public static void main(String[] args) {
//        testHashMap();
        testConcurrentHashMap();
    }

    // 需求使用2连个线程，每个线程循环5次，每循环一次让Map的value增加1
    private static void testHashMap() {
        final Map<String, Integer> count = new HashMap<>();
        final CountDownLatch endLatch = new CountDownLatch(2);
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                Integer value = count.get("a");
                if (null == value) {
                    count.put("a", 1);
                } else {
                    count.put("a", value + 1);
                }
            }
            System.out.println("xx");
            endLatch.countDown();
        };
        new Thread(task).start();
        new Thread(task).start();
        try {
            endLatch.await();
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void testConcurrentHashMap() {
        final Map<String, Integer> count = new ConcurrentHashMap<>();
        final CountDownLatch endLatch = new CountDownLatch(2);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                Integer oldValue, newValue;
                for (int i = 0; i < 5; i++) {
                    while (true) {
                        oldValue = count.get("a");
                        if (null == oldValue) {
                            newValue = 1;
                            if (count.putIfAbsent("a", newValue) == null) { // 第一次的时候次数设置成1
                                break;
                            }
                        } else {
                            newValue = oldValue + 1; // 后面每次数量+1
                            if (count.replace("a", oldValue, newValue)) {
                                break;
                            }
                        }
                    }
                }
                System.out.println("xx");
                endLatch.countDown();
            }
        };
        new Thread(task).start();
        new Thread(task).start();
        try {
            endLatch.await();
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
