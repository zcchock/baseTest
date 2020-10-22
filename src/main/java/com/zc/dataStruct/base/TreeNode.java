package com.zc.dataStruct.base;

/**
 * @author chock
 * @since 2019/9/24
 */
public class TreeNode {

    /**
     * 为方便，使用int作为数据类型
     */
    private int val;
    private TreeNode leftChild;
    private TreeNode rightChild;

    public TreeNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }
}
