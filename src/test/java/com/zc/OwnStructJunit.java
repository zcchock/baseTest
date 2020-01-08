package com.zc;


import com.zc.test.dataStruct.ownStruct.linkList.OwnDoubleLinkList;
import com.zc.test.dataStruct.ownStruct.stack.OwnStrongStack;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chock
 * @since 2019/9/18
 */
public class OwnStructJunit {

    @Test
    public void testStringEquals() {
        String str1 = new String("abc");
        String str2 = new String("abc");
        Assert.assertEquals(true, ownStringEquals(str1, str2));
    }

    @Test
    public void testStackMatch() {
        String str = "12<a[b{c}]>";
        Assert.assertEquals(true, ownStackMatch(str));
    }

    @Test
    public void testLinkMethod() {
        OwnDoubleLinkList link = new OwnDoubleLinkList();
        link.addTail("chock");
        link.addTail("cong");
        Assert.assertEquals(true, link.hasVal("chock"));

        link.addHead("haha");
        Assert.assertEquals("haha", link.getHeadVal());

        link.display();
    }

    /**
     * 测试在集合在迭代器遍历期间对原结构进行增删操作
     * 带有线程安全的集合可以，非线程安全不行
     * 因为集成的底层迭代器不一样，例如ConcurrentHashMap为BaseIterator。HashMap为HashIterator
     */
    private void testIterator() {

        Map<String, String> map = new ConcurrentHashMap<>(10);
//        Map<String, String> map = new HashMap<>();
        map.put("1", "chock");
        map.put("3", "eva");
        map.put("5", "cong");
        map.put("7", "haha");

        Iterator iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            if ("3".equalsIgnoreCase(entry.getKey())) {
                map.put("2", "add");
            }
            System.out.println(entry.getKey() + "::" + entry.getValue());
        }

    }

    /**
     * 自写String的Equals方法
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 是否相等
     */
    private boolean ownStringEquals(String str1, String str2) {

        if (str1 == str2) {
            return true;
        }

        if (str1 instanceof String && str2 instanceof String) {
            char[] arr1 = str1.toCharArray();
            char[] arr2 = str2.toCharArray();

            if (arr1.length != arr2.length) {
                return false;
            }

            int n = arr1.length - 1;
            while (n > 0) {
                if (arr1[n] != arr2[n]) {
                    return false;
                }
                n--;
            }
            return true;
        }
        return false;
    }


    /**
     * 利用自己实现的stack进行括号匹配
     *
     * @param str 字符串
     * @return 是否匹配
     */
    private boolean ownStackMatch(String str) {
        OwnStrongStack stack = new OwnStrongStack(3);
//        String str = "12<a[b{c}]>";
        char[] cha = str.toCharArray();
        for (char c : cha) {
            switch (c) {
                case '{':
                case '[':
                case '<':
                    stack.push(c);
                    break;
                case '}':
                case ']':
                case '>':
                    if (!stack.isEmpty()) {
                        char ch = (char) stack.pop();
                        if (c == '}' && ch != '{'
                                || c == ']' && ch != '['
                                || c == ')' && ch != '(') {
                            return false;
                        }
                    }
                    break;
                default:
                    break;
            }
        }

        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

}