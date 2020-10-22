package com.zc.dataStruct.ownStruct.stack;

import java.util.Arrays;

/**
 * @author chock
 * @since 2019/9/19
 */
public class OwnStrongStack {

    /**
     * 栈体
     */
    private Object[] vals;
    /**
     * 最大值
     */
    private int maxSize;
    /**
     * 当前栈顶
     */
    private int top;


    public OwnStrongStack() {
        // 提供默认大小为10
        this.maxSize = 10;
        this.vals = new Object[10];
        this.top = -1;
    }

    public OwnStrongStack(int initialCapacity) {
        // 异常处理
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("初始化大小不能小于0");
        }
        this.maxSize = initialCapacity;
        this.vals = new Object[initialCapacity];
        this.top = -1;
    }


    /**
     * 把数据压入栈
     *
     * @param val
     */
    public void push(Object val) {

        // 扩容
        isGrow(top + 1);
        vals[++top] = val;
    }

    /**
     * 弹出栈顶的数据
     *
     * @return
     */
    public Object pop() {

        if (top == -1) {
            System.out.println("当前栈中没有数据");
            return null;
        }
        return vals[top--];
    }

    /**
     * 获取当前的栈顶数据
     *
     * @return
     */
    public Object peek() {

        if (top == -1) {
            System.out.println("当前栈中没有数据");
            return null;
        }
        return vals[top];
    }

    /**
     * 返回当前的元素个数
     *
     * @return
     */
    public int length() {
        return top + 1;
    }

    /**
     * 返回当前的容量
     *
     * @return
     */
    public int maxLength() {
        return maxSize;
    }

    /**
     * 判断栈是否空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 清空栈空间
     */
    public void clear() {
        while (top >= 0) {
            vals[top--] = null;
        }
    }

    /**
     * 内部扩容方法
     *
     * @param needCapacity
     */
    private void isGrow(int needCapacity) {

        if (needCapacity < maxSize) {
            return;
        }

        int oldCapacity = maxSize;
        int newCapacity = oldCapacity << 1;
        if (newCapacity > Integer.MAX_VALUE) {
            newCapacity = Integer.MAX_VALUE;
        }

        maxSize = newCapacity;
        vals = Arrays.copyOf(vals, newCapacity);
    }

}
