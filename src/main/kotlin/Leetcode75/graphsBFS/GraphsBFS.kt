package Leetcode75.graphsBFS


class GraphsBFS {

    /***
     * You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+').
     * You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of
     * the cell you are initially standing at.
     *
     * In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot
     * step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that
     * is at the border of the maze. The entrance does not count as an exit.
     *
     * Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.
     *
     * Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
     * Output: 1
     */
    fun nearestExit(maze: Array<CharArray>, entrance: IntArray): Int {
        val directions= arrayOf(intArrayOf(-1,0), intArrayOf(1,0), intArrayOf(0,1), intArrayOf(0,-1))
        val m=maze.size
        val n=maze[0].size
        val queue= ArrayDeque<IntArray>()

        queue.add(entrance)
        maze[entrance[0]][entrance[1]]='+'
        var steps=0
        while (queue.isNotEmpty()){
            val size=queue.size
            steps++
            repeat(size){ i->
                val position=queue.removeFirst()
                for(direction in directions){
                    val row=position[0]+direction[0]
                    val column=position[1]+direction[1]

                    if(row<0 ||row>m-1 ||column<0 ||column>n-1) continue
                    if(maze[row][column]=='+')continue
                    if(row==0 ||row==m-1 ||column==0||column==n-1) return steps
                    queue.add(intArrayOf(row,column))
                    maze[row][column]='+'
                }

            }
        }
        return -1
    }

    fun orangesRotting(grid: Array<IntArray>): Int {
        val queue=ArrayDeque<IntArray>()
        val rottedOranges=findRotted(grid)
        val directions= arrayOf(intArrayOf(-1,0), intArrayOf(1,0), intArrayOf(0,-1), intArrayOf(0,1))
        val m=grid.size
        val n=grid[0].size

        var steps=0

        rottedOranges.forEach { array->
            queue.addLast(array)
        }

        while (queue.isNotEmpty()){
            val size=queue.size
            steps++
            repeat(size){
                val currPosition=queue.removeFirst()
                for(direction in directions){
                    val row=currPosition[0]+direction[0]
                    val col=currPosition[1]+direction[1]

                    if(row<0||row>m-1||col<0||col>n-1)continue
                    if(grid[row][col]==0 ||grid[row][col]==2) continue
                    grid[row][col]=2
                    queue.addLast(intArrayOf(row,col))
                }
            }
        }
        if(grid.any { it.any { it==1 } })return -1
        return steps-1
    }

    fun findRotted(grid:Array<IntArray>):List<IntArray>{
        val listOfRotted= mutableListOf<IntArray>()
        grid.forEachIndexed{ i, row->
            row.forEachIndexed { j,position->
                if(grid[i][j]==2){
                    listOfRotted.add(intArrayOf(i,j))
                }
            }
        }
        return listOfRotted
    }
}

fun main(){
    val gBFS=GraphsBFS()
    println("Nearest exit:"+gBFS.nearestExit(arrayOf(charArrayOf('+','+','.','+'), charArrayOf('.','.','.','+'),charArrayOf('+','+','+','.')), intArrayOf(1,2)))
    //[[2,1,1],[1,1,0],[0,1,1]]
    println("Rotted oranges:"+gBFS.orangesRotting(arrayOf(intArrayOf(2,1,1), intArrayOf(1,1,0), intArrayOf(0,1,1))))
    //grid = [[2,1,1],[0,1,1],[1,0,1]]
    println("Rotted oranges2:"+ gBFS.orangesRotting(arrayOf(intArrayOf(2,1,1), intArrayOf(0,1,1), intArrayOf(1,0,1))))
    println("Rotted oranges3:"+ gBFS.orangesRotting(arrayOf(intArrayOf(2,1,2), intArrayOf(1,1,1), intArrayOf(0,1,1))))
}