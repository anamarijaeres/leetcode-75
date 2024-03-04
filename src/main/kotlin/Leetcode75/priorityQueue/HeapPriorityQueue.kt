package Leetcode75.priorityQueue

import java.util.PriorityQueue

class HeapPriorityQueue {
    private val pSet= sortedSetOf<Int>(1,2,3,4,5,6,7,8,9,10)
    private var theLastInt:Int?=null

    /***
     * Given an integer array nums and an integer k, return the kth largest element in the array.
     *
     * Note that it is the kth largest element in the sorted order, not the kth distinct element.
     *
     * Can you solve it without sorting?
     *
     *
     * Example 1:
     *
     * Input: nums = [3,2,1,5,6,4], k = 2
     * Output: 5
     */
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val pQueue=PriorityQueue<Int>(){ int1, int2->
            int2.compareTo(int1)
        }
        pQueue.addAll(nums.toList())
        for(i in 0 until k-1){
            pQueue.poll()
        }
        return pQueue.poll()
    }

    /***
     * You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].
     *
     * Implement the SmallestInfiniteSet class:
     *
     * SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
     * int popSmallest() Removes and returns the smallest integer contained in the infinite set.
     * void addBack(int num) Adds a positive integer num back into the infinite set, if it is not already in the infinite set.
     */
    fun popSmallest(): Int {
        theLastInt=pSet.pollFirst()
        return theLastInt!!
    }

    fun addBack(num: Int) {
        pSet.add(num)
    }
}

fun main(){
    val hPQ=HeapPriorityQueue()
    //nums = [3,2,1,5,6,4], k = 2
    println("Find kth largest element:"+ hPQ.findKthLargest(intArrayOf(3,2,1,5,6,4),2))
    //nums = [3,2,3,1,2,4,5,5,6], k = 4
    println("Find kth largest element2:"+ hPQ.findKthLargest(intArrayOf(3,2,3,1,2,4,5,5,6),4))
    /***
     * smallestInfiniteSet.addBack(2);    // 2 is already in the set, so no change is made.
     * smallestInfiniteSet.popSmallest(); // return 1, since 1 is the smallest number, and remove it from the set.
     * smallestInfiniteSet.popSmallest(); // return 2, and remove it from the set.
     * smallestInfiniteSet.popSmallest(); // return 3, and remove it from the set.
     * smallestInfiniteSet.addBack(1);    // 1 is added back to the set.
     * smallestInfiniteSet.popSmallest(); // return 1, since 1 was added back to the set and
     *                                    // is the smallest number, and remove it from the set.
     * smallestInfiniteSet.popSmallest(); // return 4, and remove it from the set.
     * smallestInfiniteSet.popSmallest();
     */
    println("Smallest infinite set:"+hPQ.addBack(2))
    hPQ.popSmallest()
    hPQ.popSmallest()
    hPQ.popSmallest()
    hPQ.addBack(1)
    hPQ.popSmallest()
    hPQ.popSmallest()
    hPQ.popSmallest()

}