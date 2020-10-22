package com.zc.dataStruct.ownStruct.stack;

/**
 * @author chock
 * @since 2019/9/19
 */
public class OwnArrayStack {

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

    /**
     * 唯一构造器，需要制定大小
     *
     * @param size
     */
    public OwnArrayStack(int size) {
        this.maxSize = size;
        this.top = -1;
        this.vals = new Object[size];
    }

    /**
     * 把数据压入栈
     *
     * @param val
     */
    public void push(Object val) {

        if (top == maxSize - 1) {
            System.out.println("当前栈满，无法新增数据");
        } else {
            vals[++top] = val;
        }
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
     * 判断栈是否满
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize;
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

}
