package com.zc.dataStruct.ownStruct.hash;

/**
 * 哈希表：线性探测
 *
 * @author chock
 * @since 2019/9/26
 */
public class OwnHashTable {

    /**
     * 每个数据项信息
     */
    private DataItem[] hashArray;
    /**
     * 数组大小
     */
    private int arraySize;
    /**
     * 数据大小
     */
    private int itemSize;
    /**
     * 删除数据项
     */
    private DataItem noItem;


    public OwnHashTable(int size) {
        this.arraySize = size;
        this.hashArray = new DataItem[size];
        this.noItem = new DataItem(-1);
    }

    public boolean isFull() {
        return itemSize == arraySize;
    }

    public boolean isEmpty() {
        return itemSize == 0;
    }

    /**
     * 计算对应的数组下标
     *
     * @param key
     * @return
     */
    public int getHash(int key) {
        return key % arraySize;
    }

    /**
     * 插入数据
     *
     * @param item
     */
    public void insert(DataItem item) {
        if (isFull()) {
            System.out.println("哈希表已满，正在扩容");
            extendHashTable();
        }
        int key = item.getKey();
        int hashVal = getHash(key);
        while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            ++hashVal;
            hashVal %= arraySize;
        }
        hashArray[hashVal] = item;
        itemSize++;
    }

    /**
     * 删除数据
     *
     * @param key
     * @return
     */
    public DataItem delete(int key) {

        if (isEmpty()) {
            System.out.println("哈希中没有任何数据");
            return null;
        }

        int hashVal = getHash(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                DataItem temp = hashArray[hashVal];
                hashArray[hashVal] = noItem;
                itemSize--;
                return temp;
            }
            // 线性往后找
            ++hashVal;
            hashVal %= arraySize;
        }

        return null;
    }

    /**
     * 查找数据
     *
     * @param key
     * @return
     */
    public DataItem find(int key) {
        int hashVal = getHash(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                return hashArray[hashVal];
            }
            ++hashVal;
            hashVal %= arraySize;
        }
        return null;
    }


    /**
     * 数组有固定的大小，而且不能扩展，所以扩展哈希表只能另外创建一个更大的数组，然后把旧数组中的数据插到新的数组中。
     * 但是哈希表是根据数组大小计算给定数据的位置的，所以这些数据项不能再放在新数组中和老数组相同的位置上。
     * 因此不能直接拷贝，需要按顺序遍历老数组，并使用insert方法向新数组中插入每个数据项。
     * 这个过程叫做重新哈希化。这是一个耗时的过程，但如果数组要进行扩展，这个过程是必须的。
     */
    private void extendHashTable() {

        int num = arraySize;
        // 赋值时重新计数
        itemSize = 0;
        arraySize *= 2;
        DataItem[] oldItems = hashArray;

        // 重建数据数组
        hashArray = new DataItem[arraySize];
        for (int i = 0; i < num; i++) {
            insert(oldItems[i]);
        }
    }


    /**
     * 打印数组内容
     */
    public void display() {
        System.out.println("Table:");
        for (int j = 0; j < arraySize; j++) {
            if (hashArray[j] != null) {
                System.out.print(hashArray[j].getKey() + " ");
            } else {
                System.out.print("** ");
            }
        }
    }

    public static class DataItem {
        private int iData;

        public DataItem(int iData) {
            this.iData = iData;
        }

        public int getKey() {
            return iData;
        }
    }

}
