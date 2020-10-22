package com.zc.dataStruct.ownStruct.hash;

import com.zc.dataStruct.base.LinkNode;

/**
 * @author chock
 * @since 2019/9/27
 */
public class OwnSortLink {

    public LinkNode first;

    public OwnSortLink() {
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 插入数据
     *
     * @param node
     */
    public void insert(LinkNode node) {
        int key = node.getKey();
        LinkNode prev = null;
        LinkNode curr = first;

        while (curr != null && curr.getKey() < key) {
            prev = curr;
            curr = curr.next;
        }

        if (prev == null) {
            first = node;
        } else {
            prev.next = node;
        }

        node.next = curr;
    }

    /**
     * 删除
     *
     * @param key
     */
    public void delete(int key) {
        LinkNode prev = null;
        LinkNode curr = first;
        if (isEmpty()) {
            System.out.println("Linked is Empty!!!");
            return;
        }
        while (curr != null && curr.getKey() != key) {
            prev = curr;
            curr = curr.next;
        }
        if (prev == null) {
            first = first.next;
        } else {
            prev.next = curr.next;
        }
    }

    /**
     * 查找
     *
     * @param key
     * @return
     */
    public LinkNode find(int key) {
        LinkNode curr = first;
        while (curr != null && curr.getKey() <= key) {
            if (curr.getKey() == key) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    public void displayLink() {
        System.out.println("Link(First->Last)");
        LinkNode current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }

}
