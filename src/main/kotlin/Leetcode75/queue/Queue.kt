package Leetcode75.queue

import java.util.LinkedList
import kotlin.math.max

class Queue {

    fun predictPartyVictory(senate: String): String {
        val rQ = LinkedList<Int>()
        val dQ = LinkedList<Int>()

        for (i in senate.indices) {
            (if (senate[i] == 'R') rQ else dQ).addLast(i)
        }

        while (rQ.isNotEmpty() && dQ.isNotEmpty()) {
            //Next voter is the one with the lowest index at the beginning
            //of both queues (retaining the initial order of voters)
            //The strategy for each voter is to ban the next voter of the
            //opposing faction (the one who is next to vote)
            //After having voted, the current voter (who still retains the
            //right to vote) is moved to the end of its party queue and
            //their index is recalculated
            val voterQ=if (rQ.first < dQ.first) rQ  else dQ
            voterQ.addLast(max(rQ.last,dQ.last)+1)
            //Finally removing the banned voter of the opposing faction
            //and the now-obsolete instance of the current voter
            rQ.removeFirst()
            dQ.removeFirst()
        }
        if (rQ.isNotEmpty()) return "Radiant"
        return "Dire"
    }
    fun predictPartyVictory2(senate: String): String {
        val qR=ArrayDeque<Int>()
        val qD=ArrayDeque<Int>()

        senate.forEachIndexed{i, c->
            if(c=='R')qR.add(i)
            else qD.add(i)
        }

        while(qR.isNotEmpty() && qD.isNotEmpty()){

            val turn=if(qR.first()<qD.first())'R' else 'D'
            if(turn=='R'){
                qR.add(max(qR.last(),qD.last())+1)
                qD.removeFirst()
                qR.removeFirst()
            }else{

                qD.add(max(qR.last(),qD.last())+1)
                qR.removeFirst()
                qD.removeFirst()
            }
        }
        return if(qR.isNotEmpty()) "Radiant" else "Dire"
    }


    fun testRecentCounter() {
        val rc = RecentCounter()

        println(rc.ping(1))//1
        println(rc.ping(100))//2
        println(rc.ping(3001))//3
        println(rc.ping(3002))//3
    }
}

class RecentCounter() {
    val queue: ArrayDeque<Int> = ArrayDeque()


    fun ping(t: Int): Int {
        while (queue.isNotEmpty() && queue.first() < t - 3000) {
            queue.removeFirst()
        }
        queue.addLast(t)
        return queue.size
    }

}

fun main() {
    val q = Queue()
    println("Recent counter testing:" + q.testRecentCounter())
    println("Predict party victory:"+ q.predictPartyVictory("RDD"))
    println("Predict party victory:"+ q.predictPartyVictory2("RDD"))
}