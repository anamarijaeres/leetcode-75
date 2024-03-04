package Leetcode75.binaryTreeDFS

import com.sun.source.tree.Tree
import kotlin.math.max

class BinaryTreeDFS {

    /***
     * Given the root of a binary tree, return its maximum depth.
     *
     * A binary tree's maximum depth is the number of nodes along the longest path from the root
     * node down to the farthest leaf node.
     */
    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        return max(maxDepth(root.left), maxDepth(root.right)) + 1
    }


    fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
        val list1 = mutableListOf<Int>()
        inorder(root1,list1)
        val list2 = mutableListOf<Int>()
        inorder(root2,list2)
        return list1==list2
    }

    fun inorder(root: TreeNode?, l: MutableList<Int>) {
        if (root == null) {
            return
        } else {
            inorder(root.left, l)
            if (root.left == null && root.right == null) {
                //leaf found
                l.add(root.value)
            }
            inorder(root.right,l)
        }
    }

}

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class TreeNode(var value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    companion object {
        fun generateInput(): TreeNode {
            val root = TreeNode(3)
            root.left = TreeNode(9)
            root.right = TreeNode(20)
            root!!.right!!.left = TreeNode(15)
            root!!.right!!.right = TreeNode(17)
            return root
        }

        fun generateInput2(): TreeNode {
            val root = TreeNode(3)
            root.left = TreeNode(10)
            root.right = TreeNode(20)
            root!!.right!!.left = TreeNode(15)
            root!!.right!!.right = TreeNode(17)
            return root
        }

        fun generateInput3(): TreeNode {
            val root = TreeNode(3)
            root.left = TreeNode(10)
            root.right = TreeNode(20)
            root!!.left!!.left = TreeNode(15)
            root!!.left!!.left!!.right = TreeNode(12)
            return root
        }

        fun generateInput4(): TreeNode {
            val root = TreeNode(3)
            root.left = TreeNode(1)
            root.right = TreeNode(4)
            root!!.left!!.left = TreeNode(3)
            root!!.right!!.left= TreeNode(1)
            root!!.right!!.right = TreeNode(5)
            return root
        }
    }
}


fun main() {
    val btDFS = BinaryTreeDFS()
    println("Max depth of binary tree:" + btDFS.maxDepth(TreeNode.generateInput()))
    println("Find similar leaves on a binary tree:"+ btDFS.leafSimilar(TreeNode.generateInput(),TreeNode.generateInput()))
    println("Find similar leaves on a binary tree2:"+ btDFS.leafSimilar(TreeNode.generateInput(),TreeNode.generateInput2()))

}