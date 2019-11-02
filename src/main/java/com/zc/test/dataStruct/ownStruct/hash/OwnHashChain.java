package com.zc.test.dataStruct.ownStruct.hash;

import com.zc.test.dataStruct.base.LinkNode;

/**
 * @author chock
 * @since 2019/9/27
 */
public class OwnHashChain {
    /**
     * 数组中存放链表
     */
    private OwnSortLink[] hashArray;
    private int arraySize;

    public OwnHashChain(int size) {
        arraySize = size;
        hashArray = new OwnSortLink[arraySize];
        //new 出每个空链表初始化数组
        for (int i = 0; i < arraySize; i++) {
            hashArray[i] = new OwnSortLink();
        }
    }

    public void displayTable() {
        for (int i = 0; i < arraySize; i++) {
            System.out.print(i + "：");
            hashArray[i].displayLink();
        }
    }

    public int hashFunction(int key) {
        return key % arraySize;
    }

    public void insert(LinkNode node) {
        int key = node.getKey();
        int hashVal = hashFunction(key);
        hashArray[hashVal].insert(node);//直接往链表中添加即可
    }

    public LinkNode delete(int key) {
        int hashVal = hashFunction(key);
        LinkNode temp = find(key);
        hashArray[hashVal].delete(key);//从链表中找到要删除的数据项，直接删除
        return temp;
    }

    public LinkNode find(int key) {
        int hashVal = hashFunction(key);
        LinkNode node = hashArray[hashVal].find(key);
        return node;
    }

}
