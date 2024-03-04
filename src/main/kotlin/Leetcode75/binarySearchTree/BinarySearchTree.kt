package Leetcode75.binarySearchTree

import Leetcode75.binaryTreeDFS.TreeNode

class BinarySearchTree {
    /***
     * You are given the root of a binary search tree (BST) and an integer val.
     *
     * Find the node in the BST that the node's value equals val and return the subtree rooted with that node.
     * If such a node does not exist, return null.
     *
     * Input: root = [4,2,7,1,3], val = 2
     * Output: [2,1,3]
     */
    fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
        if(root==null) return null
        else{
            if(`val`==root.value){
                return root
            }else {
                if (`val` < root.value) {
                    return searchBST(root.left, `val`)
                } else {
                    return searchBST(root.right,`val`)
                }
            }
        }

    }
}

fun main(){
    val bst=BinarySearchTree()
    val resultNode=bst.searchBST(TreeNode.generateInput(),20)
    println("Search the bst:"+resultNode?.value)

}