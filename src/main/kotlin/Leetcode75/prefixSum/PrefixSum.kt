package Leetcode75.prefixSum

import kotlin.math.max

class PrefixSum {

    /***
     * There is a biker going on a road trip. The road trip consists of n + 1 points at different altitudes.
     * The biker starts his trip on point 0 with altitude equal 0.
     *
     * You are given an integer array gain of length n where gain[i] is the net gain in altitude between points
     * i​​​​​​ and i + 1 for all (0 <= i < n). Return the highest altitude of a point.
     *
     *
     *
     * Example 1:
     *
     * Input: gain = [-5,1,5,0,-7]
     * Output: 1
     * Explanation: The altitudes are [0,-5,-4,1,1,-6]. The highest is 1.
     */
    fun largestAltitude(gain: IntArray): Int {
        var currentAltitude=0
        var maxAlt=currentAltitude
        gain.forEach{ gain->
            currentAltitude+=gain
            maxAlt= max(currentAltitude,maxAlt)
        }
        return maxAlt
    }

    /***
     * Given an array of integers nums, calculate the pivot index of this array.
     *
     * The pivot index is the index where the sum of all the numbers strictly to the left of the
     * index is equal to the sum of all the numbers strictly to the index's right.
     *
     * If the index is on the left edge of the array, then the left sum is 0 because there are no
     * elements to the left. This also applies to the right edge of the array.
     *
     * Return the leftmost pivot index. If no such index exists, return -1.
     *
     * Example 1:
     *
     * Input: nums = [1,7,3,6,5,6]
     * Output: 3
     * Explanation:
     * The pivot index is 3.
     * Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
     * Right sum = nums[4] + nums[5] = 5 + 6 = 11
     */
    fun pivotIndex(nums: IntArray): Int {
        val prefixSum=IntArray(nums.size){0}
        val postfixSum=IntArray(nums.size){0}

        nums.forEachIndexed { index, num ->
            if(index==0){
                postfixSum[index]=nums.foldIndexed(0) {i, acc, num1->
                    if(i==0){
                        0
                    }else{
                        acc+num1
                    }
                }
            }else {
                //calculate prefix
                prefixSum[index] = prefixSum[index-1]+nums[index-1]
                postfixSum[index]=postfixSum[index-1]-num
                if(prefixSum[index]==postfixSum[index]){
                    return index
                }
            }
        }
        return -1
    }
}

fun main(){
    val prefixSum=PrefixSum()
    println("Largest altitude:"+prefixSum.largestAltitude(intArrayOf(-5,1,5,0,-7)))
    println("Find pivot: "+prefixSum.pivotIndex(intArrayOf(1,7,3,6,5,6)))

}