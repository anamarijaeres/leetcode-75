package Leetcode75.binaryTreeBFS

import Leetcode75.binaryTreeDFS.TreeNode
import java.util.*
import kotlin.collections.ArrayDeque

class BinaryTreeBFS {
    private var ans=0
    private var max=0

    /***
     * Given the root of a binary tree, imagine yourself standing on the right side of it,
     * return the values of the nodes you can see ordered from top to bottom.
     *
     * Input: root = [1,2,3,null,5,null,4]
     * Output: [1,3,4]
     *
     */
    fun rightSideView(root: TreeNode?): List<Int> {
        val queue = ArrayDeque<TreeNode>()
        val result = mutableListOf<Int>()
        if (root == null) return emptyList()
        else {
            queue.addLast(root)

            while (queue.isNotEmpty()) {
                val size = queue.size
                result.add(queue.last().value)
                repeat(size) {
                    val curr = queue.removeFirst()
                    curr.left?.let { l -> queue.addLast(l) }
                    curr.right?.let { r -> queue.addLast(r) }
                }
            }
        }
        return result
    }

    fun rightSideView2(root: TreeNode?): List<Int> {
        val q=LinkedList<TreeNode>()
        val rView= mutableListOf<Int>()
        root?.let { q.add(root) }?: return emptyList()
        while(q.isNotEmpty()){
            val n=q.size
            rView.add(q.last().value)
            repeat(n){
                val curr=q.removeFirst()
                curr.left?.let {l-> q.add(l) }
                curr.right?.let{ r-> q.add(r)}
            }
        }
        return rView
    }

    /***
     * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
     *
     * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
     *
     * Input: root = [1,7,0,7,-8,null,null]
     * Output: 2
     * Explanation:
     * Level 1 sum = 1.
     * Level 2 sum = 7 + 0 = 7.
     * Level 3 sum = 7 + -8 = -1.
     * So we return the level with the maximum sum which is level 2.
     */
    fun maxLevelSum(root: TreeNode?): Int {
        val q = ArrayDeque<TreeNode>()
        val sumOfElements = mutableListOf<Int>()
        if (root == null) return -1
        else {
            q.add(root)
            while (q.isNotEmpty()) {
                val lvlSize = q.size
                var sum = 0
                for (i in 0 until lvlSize) {
                    sum += q.first().value
                    val curr = q.removeFirst()
                    curr.left?.let { l -> q.addLast(l) }
                    curr.right?.let { r -> q.addLast(r) }
                }
                sumOfElements.add(sum)
            }
        }
        return sumOfElements.max()
    }

    fun goodNodes(root: TreeNode?): Int {
        if(root==null) return ans
        else{
            if(root.value>=max){
                ans++
                max=root.value
            }
        }
        goodNodes(root?.right)
        goodNodes(root?.left)
        return ans
    }


}


fun main() {
    val btBFS = BinaryTreeBFS()
    println("Right side view:" + btBFS.rightSideView(TreeNode.generateInput3()))
    println("Max level sum:" + btBFS.maxLevelSum(TreeNode.generateInput()))
    println("Count" + btBFS.goodNodes(TreeNode.generateInput4()))
}