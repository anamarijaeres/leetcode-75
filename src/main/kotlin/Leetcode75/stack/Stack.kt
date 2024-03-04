package Leetcode75.stack

import java.util.LinkedList
import java.util.Stack
import kotlin.math.abs
import kotlin.math.absoluteValue

class Stack {

    /***
     * You are given a string s, which contains stars *.
     *
     * In one operation, you can:
     *
     * Choose a star in s.
     * Remove the closest non-star character to its left, as well as remove the star itself.
     * Return the string after all stars have been removed.
     *
     * Input: s = "leet**cod*e"
     * Output: "lecoe"
     */
    fun removeStars(s: String): String {
        val stack = Stack<Char>()
        s.forEach { character ->
            if (character != '*') {
                stack.push(character)

            } else {
                stack.pop()
            }
        }
        return stack.toCharArray().joinToString("")
//        var newS=""
//        while (stack.isNotEmpty()){
//            newS+=stack.pop()
//        }
//        return newS.reversed()
    }

    fun removeStarsWithLinkedList(s: String): String {
        val ll = LinkedList<Char>()

        for (c in s) {
            if (c == '*') {
                ll.removeLast()
            } else {
                ll.add(c)
            }
        }
        return ll.joinToString("")
    }

    /***
     * We are given an array asteroids of integers representing asteroids in a row.
     *
     * For each asteroid, the absolute value represents its size, and the sign represents its direction
     * (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
     *
     * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
     * If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
     *
     *
     *
     * Example 1:
     *
     * Input: asteroids = [5,10,-5]
     * Output: [5,10]
     * Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
     */
    fun asteroidCollision(asteroids: IntArray): IntArray {
        val stack = Stack<Int>()

        for (a in asteroids) {
            if (stack.isNotEmpty()) {
                if (stack.peek() * a < 0) {
                    //collision- one is pos and one is neg
                    if (stack.peek().absoluteValue < a.absoluteValue) {
                        stack.pop()
                        stack.push(a)
                    }
                } else {
                    //they are going in the same direction
                    stack.push(a)
                }
            } else {
                //nothing is on the stack yet
                stack.push(a)
            }
        }
        val res = stack.toIntArray()
        return res
    }

    //CORRECT VERSION
    fun asteroidCollision2(asteroids: IntArray): IntArray {
        val stack = Stack<Int>()

        asteroids.forEach{num->
            if(stack.isEmpty()) stack.push(num)
            else{
                if(stack.peek()*num<0){
                    //collision
                    while(stack.isNotEmpty()&&stack.peek()*num<0 ){
                        //calculate which one stays
                        if(abs(stack.peek())>abs(num)){
                            //don't push num and exit while
                            break
                        }else{
                            if(abs(stack.peek())==abs(num)){
                                stack.pop()
                                break
                            }else{
                                stack.pop()
                            }
                        }
                    }
                    if(stack.isNotEmpty()&& stack.peek()*num>0 ){
                        stack.push(num)
                    }
                }else{
                    stack.push(num)
                }

            }
        }
        return stack.toIntArray()
    }

    fun decodeString(s: String): String {
        val stack=Stack<Char>()
        var count=0
        var pattern=""
        var i=0
        var res=""
        while(i<s.length){
            if(!s[i].isLetter()){
                stack.push(s[i++])
            }else{
                while(s[i].isLetter()){
                    pattern+=s[i++].toString()
                }
                if(s[i++]==']'){
                    stack.pop()
                }
                count=stack.pop().toString().toInt()
                for(j in 0 until count){
                    res+=pattern
                }
                pattern=""
            }
        }
        return res
    }
}

fun main() {
    val stackExercises = Leetcode75.stack.Stack()
    println("Remove starts:" + stackExercises.removeStars("leet**cod*e"))
    println("Remove starts with linked list:" + stackExercises.removeStarsWithLinkedList("leet**cod*e"))
    println("Asteroid collision: " + stackExercises.asteroidCollision2(intArrayOf(10, 2, -5)))
    println("Decode:"+ stackExercises.decodeString("3[a]2[bc]"))
}