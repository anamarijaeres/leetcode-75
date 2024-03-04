package Leetcode75.twoPointers

class TwoPointers {


    /**
     * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     *
     * Note that you must do this in-place without making a copy of the array.
     *
     * Example 1:
     *
     * Input: nums = [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     *
     * The goal is to move all zeroes to the end of the array while preserving the relative order of the non-zero elements.
     * To achieve this in-place, the code uses two pointers (i and j) to iterate through the array.
     * The variable i is responsible for finding non-zero elements, and j is responsible for finding zero elements.
     * Whenever a non-zero element is found at index i and a zero element is found at index j (with i < j), they are swapped. This process continues until the end of the array is reached.
     */
    fun moveZeroes(nums: IntArray): IntArray {
        var i = 0
        var j = 0
        while (i < nums.size && j < nums.size) {
            if (nums[i] == 0) {
                if (nums[j] != 0) {
                    val temp = nums[i]
                    nums[i] = nums[j]
                    nums[j] = temp
                    i++
                }
            } else {
                ++i
            }
            ++j
        }
        return nums
    }

    /***
     * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
     *
     * A subsequence of a string is a new string that is formed from the original string by deleting
     * some (can be none) of the characters without disturbing the relative positions of the remaining characters.
     * (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
     *
     *
     * Example 1:
     *
     * Input: s = "abc", t = "ahbgdc"
     * Output: true
     *
     */
    fun isSubsequence(s: String, t: String): Boolean {
        if(s.length>t.length) return false
        var sP=0
        var tP=0
        while(sP<s.length && tP<t.length){
            if(s[sP]==t[tP]){
                sP++
            }
            tP++
        }
        return sP==s.length
    }
}

fun main() {
    val twoPointers = TwoPointers()
    println("Move zeros to the end:" + twoPointers.moveZeroes(intArrayOf(0, 1, 0, 3, 12)))
    println("Is subsequence:"+ twoPointers.isSubsequence("abc","ahbgdc"))
}