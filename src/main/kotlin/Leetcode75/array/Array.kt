package Leetcode75.array

class Array {


    /***
     * You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. If a string is longer than the other, append the additional letters onto the end of the merged string.
     *
     * Return the merged string.
     *
     *
     *
     * Example 1:
     *
     * Input: word1 = "abc", word2 = "pqr"
     * Output: "apbqcr"
     * Explanation: The merged string will be merged as so:
     * word1:  a   b   c
     * word2:    p   q   r
     * merged: a p b q c r
     */
    fun mergeAlternately(word1: String, word2: String): String {
        var result = ""
        for (i in word1.indices) {
            result += word1[i]
            if (i < word2.length) {
                result += word2[i]
            }
        }
        if (word1.length < word2.length) {
            result += word2.substring(word1.length)
        }
        return result
    }

    /***
     *
     * For two strings s and t, we say "t divides s"
     * if and only if s = t + ... + t (i.e., t is concatenated with itself one or more times).
     *
     * Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
     *
     *
     *
     * Example 1:
     *
     * Input: str1 = "ABCABC", str2 = "ABC"
     * Output: "ABC"
     */
    fun gcdOfStrings(str1: String, str2: String): String {
        if (str1 + str2 != str2 + str1) return ""
        return str1.substring(0, gcd(str1.length, str2.length))
    }

    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }

    /***
     *There are n kids with candies. You are given an integer array candies,
     * where each candies[i] represents the number of candies the ith kid has,
     * and an integer extraCandies, denoting the number of extra candies that you have.
     *
     * Return a boolean array result of length n, where result[i] is true if, after giving
     * the ith kid all the extraCandies, they will have the greatest number of candies
     * among all the kids, or false otherwise.
     *
     * Note that multiple kids can have the greatest number of candies.
     *
     * Example 1:
     *
     * Input: candies = [2,3,5,1,3], extraCandies = 3
     * Output: [true,true,true,false,true]
     */
    fun kidsWithCandies(candies: IntArray, extraCandies: Int): List<Boolean> {
        val maxC = candies.maxOrNull()
        maxC?.let {
            return candies.map { (it + extraCandies) >= maxC }
        }
        return emptyList()
    }

    /**
     * You have a long flowerbed in which some of the plots are planted, and some are not.
     * However, flowers cannot be planted in adjacent plots.
     *
     * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1
     * means not empty, and an integer n, return true if n new flowers can be planted in
     * the flowerbed without violating the no-adjacent-flowers rule and false otherwise.
     *
     * Example 1:
     *
     * Input: flowerbed = [1,0,0,0,1], n = 1
     * Output: true
     */
    fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
        var i = 0
        var numToPlace = n
        while (numToPlace > 0 && i < flowerbed.size) {
            when (i) {
                0 -> {
                    if (flowerbed[i] == 0) {
                        if(i+1==flowerbed.size) {
                            flowerbed[i] = 1
                            numToPlace--
                        }else{
                            if(flowerbed[i + 1] == 0){
                                flowerbed[i] = 1
                                numToPlace--
                            }
                        }
                    }
                }

                flowerbed.size - 1 -> {
                    if (flowerbed[i] == 0 && flowerbed[i - 1] == 0) {
                        flowerbed[i] = 1
                        numToPlace--
                    }
                }

                else -> {
                    if (flowerbed[i] == 0 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                        flowerbed[i] = 1
                        numToPlace--
                    }
                }
            }
            i++
        }


        return numToPlace == 0
    }

    /**
     * Given a string s, reverse only all the vowels in the string and return it.
     *
     * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
     *
     * Example 1:
     *
     * Input: s = "hello"
     * Output: "holle"
     * Example 2:
     *
     * Input: s = "leetcode"
     * Output: "leotcede"
     */
    fun reverseVowels(s: String): String {

        val reversedVowels=s.filter { it.isVowel() }.reversed()
        var counter=0
        return s.map { it.takeUnless { it.isVowel() }?:reversedVowels[counter++]}.joinToString("")
    }
    private fun Char.isVowel():Boolean{
        val listOfVo= listOf('a','e','i','o','u','A','E','I','O','U')
        return listOfVo.contains(this)
    }

    fun reverseWords(s: String): String {
        val words=s.trim().replace("\\s+".toRegex(), " ").split(" ")
        return words.reversed().joinToString(" ")
    }

    fun compress(chars: CharArray): Int {
        var len=0
        var counter=0
        var i=0
        while(i<chars.size){
            chars[len++]=chars[i]
            counter++
            while(i+1<chars.size && chars[i+1]==chars[i]){
                counter++
                i++
            }
            if(counter>1){
                val c=counter.toString()
                chars[len++]=c.toCharArray()[0]
                i++
            }
            counter=0
        }
        return len
    }
}


fun main() {
    val arrayExercises = Array()
    println("Merge alternately:" + arrayExercises.mergeAlternately("abtr", "c"))
    println("Greates common divisor:" + arrayExercises.gcdOfStrings("ABABAB", "ABAB"))
    println("Kid with extra candies:" + arrayExercises.kidsWithCandies(intArrayOf(2, 3, 5, 1, 3), 3))
    println("Can place flowers:" + arrayExercises.canPlaceFlowers(intArrayOf(0, 1, 0, 0, 1, 0), 1))
    println("Reverse vowels:"+ arrayExercises.reverseVowels("leetcode"))
    println("Reverse words:"+ arrayExercises.reverseWords("the sky is blue"))
    println("Compress"+ arrayExercises.compress(charArrayOf('a', 'a', 'b', 'b', 'c', 'c', 'c')))
}