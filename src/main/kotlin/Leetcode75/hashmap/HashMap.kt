package Leetcode75.hashmap

class HashMap {
    /***
     * Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
     *
     * answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
     * answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
     * Note that the integers in the lists may be returned in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: nums1 = [1,2,3], nums2 = [2,4,6]
     * Output: [[1,3],[4,6]]
     *
     * Protip: I could've also done 2 sets for nums1 and nums2 and then see if one set contains another and thats it
     */
    fun findDifference(nums1: IntArray, nums2: IntArray): List<List<Int>> {
        val mapOfF= mutableMapOf<Int,Int>()

        nums1.forEach { num->
            val frequency=mapOfF.getOrPut(num){ 0 }
            mapOfF[num]=frequency+1
        }
        nums2.forEach { num->
            val frequency=mapOfF.getOrPut(num){ 0 }
            mapOfF[num]=frequency+1
        }
        val allUnique=mapOfF.filter { it.value==1 }
        val uniqueInFirst=allUnique.filter { nums1.contains(it.key) }.map { it.key }
        val uniqueSecond=allUnique.filter { nums2.contains(it.key) }.map { it.key }
        return listOf(uniqueInFirst,uniqueSecond)
    }

    /***
     * Given an array of integers arr, return true if the number of occurrences of each value
     * in the array is unique or false otherwise.
     *
     * Example 1:
     *
     * Input: arr = [1,2,2,1,1,3]
     * Output: true
     * Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1.
     * No two values have the same number of occurrences.
     *
     */
    fun uniqueOccurrences(arr: IntArray): Boolean {
        val mapOfO= mutableMapOf<Int,Int>()

        val mapOfOccurencies=arr.groupBy { it }.mapValues { it.value.size }

        arr.forEach {num->
            val frequency=mapOfO.getOrPut(num){0}
            mapOfO[num]=frequency+1
        }
        println(mapOfO==mapOfOccurencies)

        val sizeOfDistinctValues=mapOfO.map { it.value }.distinct()
        val setOfValues:Set<Int> =mapOfO.values.toSet()
        return setOfValues.size==mapOfO.keys.size
    }

    /***
     * Two strings are considered close if you can attain one from the other using the following operations:
     *
     * Operation 1: Swap any two existing characters.
     * For example, abcde -> aecdb
     * Operation 2: Transform every occurrence of one existing character into another existing character,
     * and do the same with the other character.
     * For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
     * You can use the operations on either string as many times as necessary.
     *
     * Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
     *
     *
     *
     * Example 1:
     *
     * Input: word1 = "abc", word2 = "bca"
     * Output: true
     * Explanation: You can attain word2 from word1 in 2 operations.
     * Apply Operation 1: "abc" -> "acb"
     * Apply Operation 1: "acb" -> "bca"
     */
    fun closeStrings(word1: String, word2: String): Boolean {
        if(word1.length!=word2.length) return false
        val hmap1=mutableMapOf<Char,Int>()
        val hmap2=mutableMapOf<Char,Int>()

        word1.forEach{
                c->
            val f=hmap1.getOrPut(c){0}
            hmap1[c]=f+1
        }

        word2.forEach{
                c->
            val f=hmap2.getOrPut(c){0}
            hmap2[c]=f+1
        }

        if(hmap1.keys!=hmap2.keys) return false
        val sortedVals1=hmap1.values.sorted()
        val sortedVals2=hmap2.values.sorted()
        return sortedVals1==sortedVals2
    }

    fun equalPairs(grid: Array<IntArray>): Int {
        val mapF=mutableMapOf<String,Int>()


        for(i in 0 until grid.size){
            var row=""
            var col=""
            for(j in 0 until grid.size){
                row+=grid[i][j].toString()
                col+=grid[j][i].toString()
            }
            var f=mapF.getOrPut(row){0}
            mapF[row]=f+1
            f=mapF.getOrPut(col){0}
            mapF[col]=f+1
        }


        val res= mapF.values.filter{it>=2}.map{it-1}.sum()
        return res
    }
}

fun main(){
    val hashMap=HashMap()
    println("Find differences in the given arrays:"+ hashMap.findDifference(intArrayOf(1,2,3), intArrayOf(2,4,6)))
    println("Unique number of occurencies:" + hashMap.uniqueOccurrences(intArrayOf(1,2)))
    println(" "+ hashMap.equalPairs(arrayOf(intArrayOf(3,1,2,2), intArrayOf(1,4,4,5), intArrayOf(2,4,2,2), intArrayOf(2,4,2,2))))
}