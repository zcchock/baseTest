package com.zc.test.dataStruct.ownStruct.tree;

import com.zc.test.dataStruct.base.TreeNode;

/**
 * @author chock
 * @since 2019/9/24
 */
public class BinaryTree implements Tree {

    private TreeNode root;

    @Override
    public TreeNode findByVal(int val) {

        TreeNode curr = root;
        while (curr != null) {
            if (val > curr.getVal()) {
                curr = root.getRightChild();
            } else if (val < curr.getVal()) {
                curr = root.getLeftChild();
            } else {
                return curr;
            }
        }

        return null;
    }

    @Override
    public boolean insertNode(int val) {

        TreeNode inNode = new TreeNode(val);
        if (root == null) {
            root = inNode;
            return true;
        }

        TreeNode curr = root;
        TreeNode parentNode;
        while (curr != null) {
            parentNode = curr;
            if (val < curr.getVal()) {
                curr = curr.getLeftChild();
                if (curr == null) {
                    parentNode.setLeftChild(inNode);
                    return true;
                }
            } else {
                curr = curr.getRightChild();
                if (curr == null) {
                    parentNode.setRightChild(inNode);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteNode(int val) {

        // TODO
        return false;
    }

    @Override
    public TreeNode findMax() {
        if (root == null) {
            return null;
        }
        TreeNode result = root;
        while (result.getRightChild() != null) {
            result = result.getRightChild();
        }
        return result;
    }

    @Override
    public TreeNode findMin() {
        if (root == null) {
            return null;
        }
        TreeNode result = root;
        while (result.getLeftChild() != null) {
            result = result.getLeftChild();
        }
        return result;
    }

    @Override
    public void infixOrder(TreeNode current) {
        if (current != null) {
            infixOrder(current.getLeftChild());
            System.out.print(current.getVal() + " ");
            infixOrder(current.getRightChild());
        }
    }

    @Override
    public void preOrder(TreeNode current) {
        if (current != null) {
            System.out.print(current.getVal() + " ");
            infixOrder(current.getLeftChild());
            infixOrder(current.getRightChild());
        }
    }

    @Override
    public void postOrder(TreeNode current) {
        if (current != null) {
            infixOrder(current.getLeftChild());
            infixOrder(current.getRightChild());
            System.out.print(current.getVal() + " ");
        }
    }
}
