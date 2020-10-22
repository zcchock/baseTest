package com.zc.dataStruct.ownStruct.tree;

import com.zc.dataStruct.base.TreeNode;

/**
 * @author chock
 * @since 2019/9/24
 */
public interface Tree {

    /**
     * 查找节点
     *
     * @param val
     * @return
     */
    TreeNode findByVal(int val);

    /**
     * 插入节点
     *
     * @param val
     * @return
     */
    boolean insertNode(int val);

    /**
     * 删除节点
     *
     * @param val
     * @return
     */
    boolean deleteNode(int val);

    /**
     * 查找极值
     *
     * @return
     */
    TreeNode findMax();

    TreeNode findMin();


    /**
     * 中序遍历
     *
     * @param current
     */
    void infixOrder(TreeNode current);

    /**
     * 前序遍历
     *
     * @param current
     */
    void preOrder(TreeNode current);

    /**
     * 后序遍历
     *
     * @param current
     */
    void postOrder(TreeNode current);

}
