package com.zc.dataStruct.base;

/**
 * @author chock
 * @since 2019/9/20
 */
public class Node {

    /**
     * 节点值
     */
    private Object val;
    /**
     * 前序节点
     */
    private Node prev;
    /**
     * 后序节点
     */
    private Node next;

    public Node(Object val) {
        this.val = val;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
