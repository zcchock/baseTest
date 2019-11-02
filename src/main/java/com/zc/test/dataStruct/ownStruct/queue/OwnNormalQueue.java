package com.zc.test.dataStruct.ownStruct.queue;

/**
 * @author chock
 * @since 2019/9/19
 */
public class OwnNormalQueue {

    private Object[] vals;
    private int head;
    private int tail;
    private int maxSize;
    private int nowSize;

    public OwnNormalQueue() {
        this.head = 0;
        this.tail = -1;
        this.vals = new Object[10];
        this.maxSize = 10;
        this.nowSize = 0;
    }

    public OwnNormalQueue(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("队列大小必须大于0");
        }
        this.head = 0;
        this.tail = -1;
        this.vals = new Object[length];
        this.maxSize = length;
        this.nowSize = 0;
    }

    /**
     * 从队尾入队
     *
     * @param val
     */
    public void insert(Object val) {
        if (isFull()) {
            System.out.println("队列已满，无法插入新的数据");
            return;
        }
        if (tail == maxSize - 1) {
            tail = -1;
        }
        vals[++tail] = val;
        nowSize++;
    }

    /**
     * 队头入队，类似于插队
     *
     * @param val
     */
    public void insertReverse(Object val) {
        if (isFull()) {
            System.out.println("队列已满，无法插入新的数据");
            return;
        }
        if (head == 0) {
            head = maxSize;
        }
        vals[head--] = val;
        nowSize++;
    }

    /**
     * 删除队头元素，并返回该值
     *
     * @return
     */
    public Object remove() {
        if (isEmpty()) {
            System.out.println("队列为空，无法移除数据");
            return null;
        }

        Object result = vals[head];
        if (head == maxSize - 1) {
            head = 0;
        } else {
            head++;
        }
        nowSize--;
        return result;
    }

    /**
     * 从队尾移除，类似于放弃排队
     *
     * @return
     */
    public Object removeReverse() {
        if (isEmpty()) {
            System.out.println("队列为空，无法移除数据");
            return null;
        }

        Object result = vals[tail];
        if (tail == 0) {
            tail = maxSize - 1;
        } else {
            tail++;
        }
        nowSize--;
        return result;
    }

    public Object peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return vals[head];
    }

    public Object peekLast() {
        if (isEmpty()) {
            return null;
        }
        return vals[tail];
    }

    /**
     * 判断队列是否空
     *
     * @return
     */
    private boolean isEmpty() {
        return nowSize == 0;
    }

    /**
     * 判断队列是否满
     *
     * @return
     */
    private boolean isFull() {
        return maxSize == nowSize;
    }

    /**
     * 返回当前有效值数量
     *
     * @return
     */
    private int size() {
        return nowSize;
    }
}
