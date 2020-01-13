package com.zc.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chock
 * @since 2020/1/9
 */
public class GuavaTest {

    /**
     * Joiner是连接器，Splitter是分割器，通常我们会把它们定义为static final，
     * 利用on生成对象后在应用到String进行处理，这是可以复用的
     */
//    private static final Joiner joiner = Joiner.on(",").useForNull("空");
    private static final Joiner joiner = Joiner.on(",").skipNulls();

    private static final Splitter splitter = Splitter.on(",");

    @Test
    public void joinerGuava() {

        String[] arrStr = new String[]{"chock", "cong", null, null, "GD"};
        // String方法
        System.out.println(String.join(",", arrStr));
        // Guava方法
        System.out.println(joiner.join(arrStr));

        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        System.out.println(Joiner.on(",").withKeyValueSeparator("=").join(map));

        StringBuilder sb = new StringBuilder();
        joiner.appendTo(sb, arrStr);
        System.out.println(sb);

    }

    @Test
    public void splitterGuava() {
        String str = "chock,cong,null,null,GD";
        String strMap = "key1=value1,key2=value2,key3=value3";
        Map<String, String> map = splitter.withKeyValueSeparator("=").split(strMap);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            System.out.println(stringStringEntry.getKey() + " " + stringStringEntry.getValue());
        }
    }

    @Test
    public void optionalGuava() {
        Integer value1 = null;
        Integer value2 = 10;
       /*创建指定引用的Optional实例，若引用为null则快速失败返回absent()
         absent()创建引用缺失的Optional实例
        */
        Optional<Integer> a = Optional.fromNullable(value1);
        Optional<Integer> b = Optional.of(value2); //返回包含给定的非空引用Optional实例
        System.out.println(sum(a, b));
    }

    private static Integer sum(Optional<Integer> a, Optional<Integer> b) {
        //isPresent():如果Optional包含非null的引用（引用存在），返回true
        System.out.println("First param is present: " + a.isPresent());
        System.out.println("Second param is present: " + b.isPresent());
        Integer value1 = a.or(0);  //返回Optional所包含的引用,若引用缺失,返回指定的值
        Integer value2 = b.get(); //返回所包含的实例,它必须存在,通常在调用该方法时会调用isPresent()判断是否为null
        return value1 + value2;
    }

    @Test
    public void preconditionGuava() {
        try {
            getValue(5);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        try {
            sum2(4, null);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        try {
            sqrt(-1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static double sqrt(double input) {
        Preconditions.checkArgument(input > 0.0,
                "Illegal Argument passed: Negative value %s.", input);
        return Math.sqrt(input);
    }

    private static int sum2(Integer a, Integer b) {
        a = Preconditions.checkNotNull(a,
                "Illegal Argument passed: First parameter is Null.");
        b = Preconditions.checkNotNull(b,
                "Illegal Argument passed: First parameter is Null.");
        return a + b;
    }

    private static int getValue(int input) {
        int[] data = {1, 2, 3, 4, 5};
        int index = Preconditions.checkElementIndex(input, data.length,
                "Illegal Argument passed: Invalid index.");
        return data[index];
    }

}
