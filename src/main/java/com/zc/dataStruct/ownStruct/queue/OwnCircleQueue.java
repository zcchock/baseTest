package com.zc.dataStruct.ownStruct.queue;

/**
 * @author chock
 * @since 2019/11/14
 */
public class OwnCircleQueue {
    private Object[] arr;
    private int maxSize;
    private int index;
    private int head;
    private int tail;

    public OwnCircleQueue(int maxSize) {
        this.arr = new Object[maxSize + 1];
        this.head = 0;
        this.tail = 0;
        this.index = 0;
        this.maxSize = maxSize;
    }

    /**
     * 加入队列   在除法计算中   余数一定要比除数小
     *
     * @param e
     */
    public void push(Object e) {
        if (isFull()) {
            throw new RuntimeException("queue is full");
        }
        //数组的下表在循环 tail % maxSize
        arr[tail % maxSize] = e;
        tail++;//real 一直在增加
        index++;
    }

    /**
     * 出队列  余数一定要比除数小
     *
     * @return
     */
    public Object pop() {
        if (isEmpty()) {
            throw new RuntimeException("queue is null");
        }
        Object e = arr[head % maxSize];
        head++;
        index--;
        return e;
    }

    /**
     * 查询队列内元素的个数
     *
     * @return
     */
    public int size() {
        return index;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * 队列是否满了 true=满了
     *
     * @return
     */
    public boolean isFull() {
        return !isEmpty() && head == tail % maxSize;
    }
}
