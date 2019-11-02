package com.zc.test.dataStruct.ownStruct.queue;

/**
 * @author chock
 * @since 2019/9/20
 */
public class OwnPriorityQueue {
    private int maxSize;
    private int[] priQueArray;
    private int nowSize;

    public OwnPriorityQueue(int length) {
        maxSize = length;
        priQueArray = new int[maxSize];
        nowSize = 0;
    }

    /**
     * 插入数据
     *
     * @param value
     */
    public void insert(int value) {
        int j;
        if (nowSize == 0) {
            priQueArray[nowSize++] = value;
        } else {
            j = nowSize - 1;
            //选择的排序方法是插入排序，按照从大到小的顺序排列，越小的越在队列的顶端
            while (j >= 0 && value > priQueArray[j]) {
                priQueArray[j + 1] = priQueArray[j];
                j--;
            }
            priQueArray[j + 1] = value;
            nowSize++;
        }
    }

    /**
     * 移除数据,由于是按照大小排序的，所以移除数据我们指针向下移动
     * 被移除的地方由于是int类型的，不能设置为null，这里的做法是设置为 -1
     *
     * @return
     */
    public int remove() {
        int k = nowSize - 1;
        int value = priQueArray[k];
        //-1表示这个位置的数据被移除了
        priQueArray[k] = -1;
        nowSize--;
        return value;
    }

    /**
     * 查看优先级最高的元素
     *
     * @return
     */
    public int peekMin() {
        return priQueArray[nowSize - 1];
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return (nowSize == 0);
    }

    /**
     * 判断是否满了
     *
     * @return
     */
    public boolean isFull() {
        return (nowSize == maxSize);
    }

}