package com.zc.test.dataStruct.ownStruct.linkList;

import com.zc.test.dataStruct.base.Node;

/**
 * @author chock
 * @since 2019/9/20
 */
public class OwnDoubleOrderLinkList {

    private int size;
    /**
     * 头节点
     */
    private Node head;

    /**
     * 尾节点
     */
    private Node tail;

    public void insert(Integer x) {
        Node newNode = new Node(x);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            // TODO 排序
        }
        size++;
    }

    /**
     * 判断链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

}
