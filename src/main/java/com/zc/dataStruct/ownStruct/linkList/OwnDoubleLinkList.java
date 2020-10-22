package com.zc.dataStruct.ownStruct.linkList;

import com.zc.dataStruct.base.Node;

/**
 * @author chock
 * @since 2019/9/20
 */
public class OwnDoubleLinkList {

    private int size;
    /**
     * 头节点
     */
    private Node head;

    /**
     * 尾节点
     */
    private Node tail;

    public OwnDoubleLinkList() {
        size = 0;
        head = null;
        tail = null;
    }

    /**
     * 在链头插入数据
     *
     * @param x
     */
    public void addHead(Object x) {
        Node newNode = new Node(x);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
        size++;
    }

    /**
     * 在链表尾加入数据
     *
     * @param x
     */
    public void addTail(Object x) {
        Node newNode = new Node(x);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        size++;
    }

    /**
     * 删除头节点
     */
    public void deleteHead() {
        if (isEmpty()) {
            System.out.println("链表为空，无法删除");
        } else {
            Node tempNode = head.getNext();
            if (tempNode == null) {
                head = null;
                tail = null;
            } else {
                tempNode.setPrev(null);
                head = tempNode;
            }

            size--;
        }
    }

    /**
     * 删除尾节点
     */
    public void deleteTail() {
        if (isEmpty()) {
            System.out.println("链表为空，无法删除");
        } else {
            Node tempNode = tail.getPrev();
            if (tempNode == null) {
                head = null;
                tail = null;
            } else {
                tempNode.setNext(null);
                tail = tempNode;
            }

            size--;
        }
    }

    /**
     * 链表中是否存在x值
     *
     * @param x
     * @return
     */
    public boolean hasVal(Object x) {
        if (isEmpty()) {
            return false;
        }

        Node temp = head;
        while (temp != null) {
            if (x == temp.getVal()) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    /**
     * 获取链表头
     *
     * @return
     */
    public Object getHeadVal() {
        if (isEmpty()) {
            return null;
        }
        return head.getVal();
    }

    /**
     * 获取链表尾
     *
     * @return
     */
    public Object getTailVal() {
        if (isEmpty()) {
            return null;
        }
        return tail.getVal();
    }

    /**
     * 判断链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 查询长度
     *
     * @return
     */
    public int length() {
        return size;
    }

    /**
     * 打印
     */
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.getVal() + " ");
            current = current.getNext();
        }
        System.out.println("");
    }

}
