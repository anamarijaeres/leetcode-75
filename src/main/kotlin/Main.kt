import java.util.Stack
import kotlin.experimental.xor

fun main(args: Array<String>) {
    println("Two halves are alike:"+halvesAreAlike("book"))
    println("Two sum:" +twoSum(intArrayOf(1,2,3,4),3))
    println("Is palindrome:"+isPalindrome(121))
    println("Is palindrome without string conversion:"+isPalindromeWithoutConversion(-1991))
    println("Convert from roman to Int:"+romanToInt("IV"))
    println("Find the longest common prefix"+longestCommonPrefix(arrayOf("flower","flow","flight")))
    println("Parentheses validation"+isValid("()[]{}"))
    val nodes=ListNode.generateInput()
    val headNode=mergeTwoLists(nodes[0],nodes[1])
    var tempPointer=headNode
    while (tempPointer?.value!=null){
        println(tempPointer?.value)
        tempPointer=tempPointer.next
    }
    println("Remove duplicates from sorted list:"+removeDuplicates(intArrayOf(1,1,1,1,2,2,2,2,4,4)))
    var array=intArrayOf(3,2,2,3)
    println("Remove an element:" + removeElement(array,3))
    println(strStr("sadbutsad", "but"))
    println("Search for inser index:"+ searchInsert(intArrayOf(1,2,3,4,5),4))
    println("Length of last word:"+ lengthOfLastWord("   fly me   to   the moon  "))
    println("Plus one:")
    plusOne(intArrayOf(9)).forEach {
        println(it)
    }
    println("Climb stairs:"+ climbStairs(5))
    println("Merge two sorted arrays: "+ merge(intArrayOf(1,4,7,0,0,0),3, intArrayOf(3,5,6),3))
    println("Max profit:" +maxProfit(intArrayOf(7,6,4,3,1)))
    println("Is palindrome:" +isPalindrome2("A man, a plan, a canal: Panama"))
    println("Is palindrome second example:" + isPalindrome2("race a car"))
    println("is palindrome third example:"+ isPalindrome2(" "))
    println("A single number: "+ singleNumber(intArrayOf(2,2,1)))
    println("Majority sort: "+ majorityElement(intArrayOf(2,2,1,1,1,2,2)))
}

/***
You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the first half and b be the second half.

Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.

Return true if a and b are alike. Otherwise, return false.

Example 1:

Input: s = "book"
Output: true
Explanation: a = "bo" and b = "ok". a has 1 vowel and b has 1 vowel. Therefore, they are alike.
Example 2:

Input: s = "textbook"
Output: false
Explanation: a = "text" and b = "book". a has 1 vowel whereas b has 2. Therefore, they are not alike.
Notice that the vowel o is counted twice.
 ***/
fun halvesAreAlike(s: String): Boolean {
    val vouvels=listOf('a','e','i','o','u','A','E','I','O','U')
    val split= s.chunked(s.length/2)
    val left=split[0].count{it in vouvels}
    val right=split[1].count{it in vouvels}
    return left==right
}

/***
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]
 ***/
fun twoSum(nums: IntArray, target: Int): IntArray {
    val diffMap= mutableMapOf<Int,Int>()
    nums.forEachIndexed { index, int ->
        diffMap[int]?.let { firstIndex-> intArrayOf(firstIndex,index) }
        diffMap[target-int]=index
    }
    return intArrayOf()
}

/***
Given an integer x, return true if x is apalindrome, and false otherwise.
 ***/
fun isPalindrome(x: Int): Boolean {
    val stringX=x.toString()
    return stringX==stringX.reversed()
}

/***
 * Could you solve it without converting the integer to a string?
 */
fun isPalindromeWithoutConversion(x: Int): Boolean {
   val stack=Stack<Int>()

    stack.push(x%10)
    var newX=x/10

    while (newX>0){
        if(stack.peek()==newX%10){
            stack.pop()
        }else{
            stack.push(newX%10)
        }
        newX/=10
    }
    return stack.isEmpty()
}

/***
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.
 ***/
fun romanToInt(s: String): Int {
    val mapRomanToInteger= mapOf<Char, Int>(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )
    var intNumber=s.mapIndexed{index, c ->
        mapRomanToInteger[c]?.times(if(((
                    s.getOrNull(index+1)?.let {
                        notNull-> mapRomanToInteger[notNull]
                    } ?: 0 ))< mapRomanToInteger[c]!!) 1 else -1) ?: 0
    }.sum()
    return intNumber
}

/***
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".



Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
 ***/
fun longestCommonPrefix(strs: Array<String>): String {
    strs.sort()
    val compare=strs[0]
    return compare.commonPrefixWith(strs.last())
}

/***
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.

Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "()[]{}"
Output: true
Example 3:

Input: s = "(]"
Output: false
 ***/
fun isValid(s: String): Boolean {
    var stack= Stack<Char>()
    val mapOfParantheses= mapOf(
        '(' to ')',
        '{' to '}',
        '[' to ']'
    )
    stack.push(s[0])
    var newS=s.drop(1)
    while (newS.isNotEmpty()){
        if(stack.isNotEmpty()&&mapOfParantheses[stack.peek()]== newS[0]){
            stack.pop()

        }else{
            stack.push(newS[0])
        }
        newS=newS.drop(1)
    }
    return stack.isEmpty()
}

/***
 * You are given the heads of two sorted linked lists list1 and list2.
 *
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
 *
 * Return the head of the merged linked list.
 *
 * /**
 *  * Example:
 *  * var li = ListNode(5)
 *  * var v = li.`val`
 *  * Definition for singly-linked list.
 *  * class ListNode(var `val`: Int) {
 *  *     var next: ListNode? = null
 *  * }
 *  */
 *1,2,7
 * 3,4,5
 */
//recursive
fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    if(list1==null){
        return list2
    }
    if(list2==null){
        return list1
    }
    if(list1.value>list2.value){
         list2.next=mergeTwoLists(list1,list2.next)
        return list2
    }else{
        list1.next=mergeTwoLists(list1.next,list2)
        return list1
    }
}

class ListNode(var value:Int){
    var next : ListNode?=null

    companion object  {
        fun generateInput(): List<ListNode> {
            var one = ListNode(1)
            one.next= ListNode(2)
            one.next!!.next = ListNode(7)

            var two = ListNode(3)
            two.next = ListNode(4)
            two.next!!.next = ListNode(5)
            return listOf(one,two)
        }

        fun generateListWithDuplicates():ListNode {
            var one = ListNode(1)
            one.next= ListNode(1)
            one.next!!.next = ListNode(1)
            one.next!!.next!!.next = ListNode(2)
            one.next!!.next!!.next!!.next  = ListNode(2)
            one.next!!.next!!.next!!.next!!.next  = ListNode(3)

            return one
        }
    }
}

/***
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.
 *
 * Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
 *
 * Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
 * Return k
 */
fun removeDuplicates(nums: IntArray): Int {
    val listOfUniques= mutableListOf<Int>()

    nums.forEachIndexed{index, i ->
        if(index==0){
            listOfUniques.add(i)
        }else {
            if (nums[index] != nums[index - 1]) {
                listOfUniques.add(i)
            }
        }
    }
    return listOfUniques.size
}

/***
Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.

Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the elements which are not equal to val.
The remaining elements of nums are not important as well as the size of nums.
Return k.

Example 1:

Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2.
It does not matter what you leave beyond the returned k (hence they are underscores).

 */
fun removeElement(nums: IntArray, `val`: Int): Int {
    val newSortedNums=nums.sortedBy { it!=`val` }
    return nums.filter { it!=`val` }.size
}

/***
Given two strings needle and haystack,
return the index of the first occurrence of needle in haystack,
or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.
 ***/
fun strStr(haystack: String, needle: String): Int {
        return haystack.indexOf(needle)
}

//----------------------10--------------------------

/***
Given a sorted array of distinct integers and a target value, return the index if the target is found.
If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.

Example 1:

Input: nums = [1,3,5,6], target = 5
Output: 2
 */
fun searchInsert(nums: IntArray, target: Int): Int {
    return nums.fold(0){ acc, n ->
        if(n>=target) return acc
        else acc+1
    }
}

/***
Given a string s consisting of words and spaces, return the length of the last word in the string.

A word is a maximal
substring
consisting of non-space characters only.



Example 1:

Input: s = "Hello World"
Output: 5
Explanation: The last word is "World" with length 5.
 */
fun lengthOfLastWord(s: String): Int {
    val arrayOfWords=s.trim().split(regex = "\\s+".toRegex())
    return arrayOfWords.last().length
}

/***
You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.

Increment the large integer by one and return the resulting array of digits.

Example 1:

Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1,2,4].

 */
fun plusOne(digits: IntArray): IntArray {
    for(i in digits.size-1 until 0 ){
        if(digits[i]<9){
            digits[i]+=1
            return digits
        }else{
            digits[i]=0
        }
    }

    val newDigits= IntArray(digits.size+1)
    newDigits[0]=1
    return newDigits
}

/***
You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
 */
fun climbStairs(n: Int): Int {

// n = 1 -> 1 -> 1
// n = 2 -> 1+1 or 2 -> 2
// n = 3 -> 1+1+1 or 2+1 or 1+2 -> 3
// n = 4 -> 1+1+1+1 or 2+2 or 1+2+1 or 2+1+1 or 1+1+2 -> 5
// n = 5 -> 1*5 or 2+2+1 or 1+2+2 or 2+1+2 or 1+1+1+2 or 1+1+2+1 or 1+2+1+1 or 2+1+1+1 -> 8


// F(5) = F(4) + F(3)
// F(n) = F(n-1) + F(n-2)

    var map=IntArray(n+1)
    map[1]=1
    map[2]=2
    map[3]=3

    for(i in 4..n){
        map[i]=map[i-1]+map[i-2]
    }
    return map[n]
}

/***
You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function,
but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n,
where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.


Example 1:

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
 */
fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    var indexOfMerged=m+n-1
    var tempIndexOfNum1=m-1
    var tempIndexOfNum2=n-1

    while (indexOfMerged>=0) {
        nums1[indexOfMerged]= when{
            tempIndexOfNum1<0-> {
                tempIndexOfNum2 -= 1
                nums2[tempIndexOfNum2+1]
            }
            tempIndexOfNum2<0->{
                tempIndexOfNum1 -= 1
                nums1[tempIndexOfNum1+1]
            }
            nums1[tempIndexOfNum1] < nums2[tempIndexOfNum2]-> {
                tempIndexOfNum2 -= 1
                nums2[tempIndexOfNum2+1]
            }
            else -> {
                tempIndexOfNum1 -= 1
                nums1[tempIndexOfNum1+1]
            }
        }
//        if(tempIndexOfNum1<0){
//            nums1[indexOfMerged] = nums1[tempIndexOfNum2]
//            tempIndexOfNum2 -= 1
//        }
//        else {
//            if(tempIndexOfNum2<0) {
//                nums1[indexOfMerged] = nums1[tempIndexOfNum1]
//                tempIndexOfNum1 -= 1
//            }else{
//                if (nums1[tempIndexOfNum1] > nums2[tempIndexOfNum2]) {
//                    nums1[indexOfMerged] = nums1[tempIndexOfNum1]
//                    tempIndexOfNum1 -= 1
//                } else {
//                    nums1[indexOfMerged] = nums2[tempIndexOfNum2]
//                    tempIndexOfNum2 -= 1
//                }
//            }
//
//        }
        indexOfMerged -= 1
    }

//    var i = m - 1
//    var j = n - 1
//    var k = m + n -1
//    while (j >= 0) {
//        nums1[k--] = if (i < 0 || nums1[i] < nums2[j]) nums2[j--] else nums1[i--]
//    }
}

/***
You are given an array prices where prices[i] is the price
of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a
different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction.
If you cannot achieve any profit, return 0.

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 */
fun maxProfit(prices: IntArray): Int {
    prices.foldIndexed(prices[0]){ i, min, price->
        prices[i]=price-min
        if(price<min) price else min
    }
    return if(prices.max()>0) prices.max() else  0
}

/***
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
 */
fun isPalindrome2(s: String): Boolean {
    val removeNonAlphaNum=s.filter { it.isLetterOrDigit() }.lowercase()
//    return removeNonAlphaNum==removeNonAlphaNum.reversed()
    var left=0
    var right= removeNonAlphaNum.length-1
    while (left<right){
        if(removeNonAlphaNum[left]!=removeNonAlphaNum[right]){
            return false
        }else{
            left++
            right--
        }
    }
    return true
}

/***
Given a non-empty array of integers nums, every element appears twice except for one.
Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.

Example 1:

Input: nums = [2,2,1]
Output: 1
Example 2:

Input: nums = [4,1,2,1,2]
Output: 4
 */
fun singleNumber(nums: IntArray): Int {
    return nums.reduceOrNull { acc, i ->
        acc xor i
    }?: -1

//    val mapOfFrequencies= mutableMapOf<Int,Int>()
//
//    nums.forEach { number->
//        val result:Int=mapOfFrequencies.getOrDefault(number,0)
//        mapOfFrequencies[number] = result+1
//    }
//    val unique=mapOfFrequencies.filter { it.value==1 }
//    return unique.values.first()
}

/***
Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times.
You may assume that the majority element always exists in the array.

The intuition behind this approach is that if an element occurs more than n/2 times
in the array (where n is the size of the array), it will always occupy the middle position
when the array is sorted.
Therefore, we can sort the array and return the element at index n/2.

Example 1:

Input: nums = [3,2,3]
Output: 3
Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2
 */
fun majorityElement(nums: IntArray): Int {
    nums.sort()
    return nums[nums.size/2]
}


