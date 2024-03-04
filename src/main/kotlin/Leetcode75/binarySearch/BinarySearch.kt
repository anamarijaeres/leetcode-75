package Leetcode75.binarySearch

class BinarySearch {
    var pick=3

    /***
     * We are playing the Guess Game. The game is as follows:
     *
     * I pick a number from 1 to n. You have to guess which number I picked.
     *
     * Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
     *
     * You call a pre-defined API int guess(int num), which returns three possible results:
     *
     * -1: Your guess is higher than the number I picked (i.e. num > pick).
     * 1: Your guess is lower than the number I picked (i.e. num < pick).
     * 0: your guess is equal to the number I picked (i.e. num == pick).
     * Return the number that I picked.
     */
    fun guessNumber(n:Int):Int {
        var l=0
        var h=n
        var mid=(h+l)/2

        while (l<h){
            val res=guess(mid)
            when(res){
                0-> return mid
                1-> {
                    h=mid-1
                }
                -1->{
                    l=mid+1
                }
            }
            mid=(h+l)/2
        }
        return -1
    }
    fun guess(num:Int):Int{
        return num.compareTo(pick)
    }

    /***
     * You are given two positive integer arrays spells and potions, of length n and m respectively, where spells[i]
     * represents the strength of the ith spell and potions[j] represents the strength of the jth potion.
     *
     * You are also given an integer success. A spell and potion pair is considered successful if the product of their
     * strengths is at least success.
     *
     * Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair
     * with the ith spell.
     */
    fun successfulPairs(spells: IntArray, potions: IntArray, success: Long): IntArray {
        potions.sort()
        return spells.map { currentSpell->
            findMin(currentSpell,potions,success)
        }.toIntArray()
    }
    //spells = [5,1,3], potions = [1,2,3,4,5], success = 7
    fun findMin(currSpell:Int,potions: IntArray,success: Long):Int {
        var l=0
        var h=potions.size-1
        var mid=l+(h-l)/2
        while (l<=h){
            if(potions[mid]*currSpell>success){
                h=mid-1
            }
            else {
                if (potions[mid] * currSpell < success) {
                    l = mid + 1
                } else {
                    break
                }
            }
            mid=l+(h-l)/2
        }
        return potions.size-l
    }
}

fun main(){
    val bs=BinarySearch()
    println("Guessing game:"+bs.guessNumber(10))
    //spells = [5,1,3], potions = [1,2,3,4,5], success = 7
    val result=bs.successfulPairs(intArrayOf(5,1,3), intArrayOf(1,2,3,4,5), success = 7)
    println("Successful pairs of potions:"+bs.successfulPairs(intArrayOf(5,1,3), intArrayOf(1,2,3,4,5), success = 7))
}