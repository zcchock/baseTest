package com.zc.test.dataStruct.base;

/**
 * @author chock
 * @since 2019/9/27
 */
public class LinkNode {

    private int data;
    public LinkNode next;

    public LinkNode(int data) {
        this.data = data;
    }

    public int getKey() {
        return data;
    }

    public void displayLink() {
        System.out.println(data + " ");
    }
}
