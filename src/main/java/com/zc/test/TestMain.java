package com.zc.test;


import com.zc.test.dataStruct.ownStruct.stack.OwnStrongStack;

/**
 * @author chock
 * @since 2019/9/18
 */
public class TestMain {

    public static void main(String[] args) {
        OwnStrongStack stack = new OwnStrongStack(5);

        stack.push("chock");
        stack.push("hand");
        stack.push("cong");

        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.peek());

        System.out.println(stack.maxLength());

        stack.push("aa");
        stack.push("bb");
        stack.push("cc");
        stack.push("dd");

        System.out.println(stack.maxLength());

    }

}



