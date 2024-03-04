package Leetcode75.graphsDFS

import Leetcode75.hashmap.HashMap
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

class GraphsDFS {

    /***
     * There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0.
     * Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.
     *
     * When you visit a room, you may find a set of distinct keys in it. Each key has a number on it,
     * denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.
     *
     * Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i,
     * return true if you can visit all the rooms, or false otherwise.
     *
     * Example 2:
     *
     * Input: rooms = [[1,3],[3,0,1],[2],[0]]
     * Output: false
     *
     * Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.
     *
     * Input: rooms = [[1],[2],[3],[]]
     * Output: true
     */
    fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
        val hmap= mutableMapOf<Int,List<Int>>()
        rooms.forEachIndexed {index, list ->
            hmap[index]=list
        }
        val visited= mutableSetOf<Int>()

        val s= Stack<Int>()
        s.push(0)
        while (s.isNotEmpty()){
            val getKeys=hmap[s.peek()]
            if(visited.contains(s.peek()))s.pop() else visited.add(s.pop())
            getKeys!!.forEach { key->
                if(!visited.contains(key)){
                    s.push(key)
                }
            }
        }
        return visited.size==hmap.keys.size
    }

    /***
     * For every city that we visit, we visit all its direct and indirect neighbors,
     * when counting the number of provinces we skip any cities that we already visited
     */
    fun findCircleNum(isConnected: Array<IntArray>): Int {
        val visited = mutableSetOf<Int>()

        fun dfs(from: Int) {
            for(i in isConnected.indices) {
                if (isConnected[from][i] == 1 && visited.add(i)) {
                    dfs(i)
                }
            }
        }

        var provinces = 0
        for (i in isConnected.indices) {
            //I only go in the city if it has not been visited so far
            if (visited.add(i)) {
                //if I added the city i need to explore now all of its neighbours
                dfs(i)
                //after I do that I increase provinces
                provinces++
            }
        }
        return provinces
    }

    /***
     * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel
     * between two different cities (this network form a tree). Last year, The ministry of transport decided to orient
     * the roads in one direction because they are too narrow.
     *
     * Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
     *
     * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
     *
     * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
     *
     * It's guaranteed that each city can reach city 0 after reorder.
     *
     * Approach is to make graph undirected, but somehow mark the edges that are added on top of original ones.
     * This way when we traverse nodes from root, we can check if we are traversing through original or non-original edge.
     * If we are traversing through original, it means it's in the opposite direction we want it to be, so we increase our count on that.
     * It doesn't matter how we traverse the tree, the approach is same.
     */
    fun minReorder(n: Int, connections: Array<IntArray>): Int {
        val edges = getEdgesFrom(connections)

        val q = ArrayDeque<Int>()
        val visited = mutableSetOf<Int>()
        var counter = 0
        q.add(0)
        visited.add(0)
        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            edges[curr]?.forEach { next ->
                if (visited.add(next)) {
                    if (next < 0) {
                        counter++
                    }
                    q.addLast(abs(next))
                }
            }
        }
        return counter
    }

    // n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
    //Output: 3
    private fun getEdgesFrom(connections: Array<IntArray>): MutableMap<Int, MutableList<Int>> {
        val edges = mutableMapOf<Int, MutableList<Int>>()
        for ((src, dst) in connections) {
            edges.computeIfAbsent(src) { mutableListOf() }.add(dst)
            edges.computeIfAbsent(dst) { mutableListOf() }.add(-src)
        }
        return edges
    }

}

fun main(){
    val graphsDFS=GraphsDFS()
    println("Can i visit all the rooms: "+ graphsDFS.canVisitAllRooms(listOf(listOf(1,3), listOf(3,0,2), listOf(2), listOf(0))))
    ///[[1,1,0],[1,1,0],[0,0,1]]
    println("Find circle nums:"+graphsDFS.findCircleNum(arrayOf(intArrayOf(1,1,0), intArrayOf(1,1,0), intArrayOf(0,0,1))))
    println("Find edges from: "+graphsDFS.minReorder(6, arrayOf(intArrayOf(0,1), intArrayOf(1,3), intArrayOf(2,3),
        intArrayOf(4,0), intArrayOf(4,5)
    )))
}