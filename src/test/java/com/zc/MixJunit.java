package com.zc;

import lombok.Data;
import org.junit.Test;

/**
 * @author chock
 * @since 2019/11/20
 */
public class MixJunit {

    @Test
    public void internTest () {
        String str1 = new StringBuilder("i'm").append(" T").toString();
        System.out.println(str1.intern()==str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern()==str2);
    }

    public static void main(String[] args) {
        System.out.println(test().toString());
    }

    private static NumClass test () {
        NumClass numClass = new NumClass(0, 1);
        try {
            numClass.setA(10 / 0);// a = 10 / 0; （抛出异常）
            numClass.setB(2);
            return numClass;
        } catch (Exception e) {
            numClass.setA(10 + 5);
        } finally {
            numClass.setA(10 - 5);
        }
        return new NumClass(3, 3);
    }

    @Data
    static class NumClass {
        int a;
        int b;

        public NumClass(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "NumClass{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }
}
