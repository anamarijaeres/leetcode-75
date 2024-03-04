package Leetcode75.slidingWindow

import kotlin.math.max

class SlidingWindow {

    /***
     * You are given an integer array nums consisting of n elements, and an integer k.
     *
     * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
     * Any answer with a calculation error less than 10-5 will be accepted.
     * Example 1:
     *
     * Input: nums = [1,12,-5,-6,50,3], k = 4
     * Output: 12.75000
     * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
     * Example 2:
     *
     * Input: nums = [5], k = 1
     * Output: 5.00000
     *
     * The idea is to use a sliding window technique. By using this algorithm,
     * we avoid recalculating the sum of subarrays from scratch for each positions,
     * allowing us to efficiently find the maximum average subarray.
     */
    fun findMaxAverage(nums: IntArray, k: Int): Double {
        var answer=0
        var sum=0

        for(i in 0 until k){
            sum+=nums[i]
        }
        var curMax:Int=sum
        for(i in k until nums.size){
            sum+=nums[i]-nums[i-k]
            curMax= max(curMax,sum)
        }

        return curMax.toDouble()/k
    }

    /***
     * Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
     *
     * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "abciiidef", k = 3
     * Output: 3
     * Explanation: The substring "iii" contains 3 vowel letters.
     */
    fun maxVowels(s: String, k: Int): Int {
        var ls=s.lowercase()
        var maxN=0
        var counter=0
        for(i in 0 until k){
            if(s[i].isVowel()){
                counter++
            }
        }
        maxN=max(maxN,counter)
        if(maxN==k)return maxN
        for(i in k until s.length){
            if(s[i].isVowel()){
                counter++
            }
            if(s[i-k].isVowel()){
                counter--
            }
            maxN=max(maxN,counter)

        }
        return maxN
    }
    fun Char.isVowel(): Boolean{
        val vowels=listOf('a','e','i','o','u')
        return vowels.contains(this)
    }

    fun longestOnes(nums: IntArray, k: Int): Int {
        var maxN=0
        var size=0
        var vk=k
        for(i in nums.indices){
            if(nums[i]==1){

                    if(nums[i-size]==1 && i!=size){
                        //do nothing
                    }else {

                        size++
                        maxN = max(maxN, size)
                    }
            }else{
                if(vk>0){
                    vk--
                    size++
                    maxN=max(maxN,size)
                }else{
                    if(nums[i-size]==0){
                        //everything stays the same
                    }else{
                        size--
                        maxN=max(maxN,size)
                    }
                }
            }
        }
        return maxN
    }
}

fun main(){
    val slidingWindow=SlidingWindow()
    println("Find maximum average with sliding window:"+ slidingWindow.findMaxAverage(intArrayOf(1,12,-5,-6,50,3),4))
    println("Longest ones:"+slidingWindow.longestOnes(intArrayOf(1,1,1,0,0,0,1,1,1,1,0),2))
}