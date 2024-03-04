package Leetcode75.graphsBFS

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*


class GraphsBFSTest {
    private val gBFS=GraphsBFS()

    @Test
    fun nearestExit() {
        val maze = arrayOf(
            charArrayOf('+', '.', '+', '+', '+', '+'),
            charArrayOf('+', '.', '+', '+', '+', '+'),
            charArrayOf('+', '.', '+', '+', '+', '+'),
            charArrayOf('+', '.', '.', '.', '.', '.')
        )
        val entrance = intArrayOf(1, 1)
        assertEquals(1, gBFS.nearestExit(maze, entrance))

        val maze2 = arrayOf(
            charArrayOf('+', '+', '+', '+', '+', '+'),
            charArrayOf('+', '.', '.', '.', '.', '+'),
            charArrayOf('+', '+', '+', '+', '+', '+')
        )
        val entrance2 = intArrayOf(1, 2)
        assertEquals(-1, gBFS.nearestExit(maze2, entrance2))
    }

    @Test
    fun orangesRotting() {
        val grid = arrayOf(
            intArrayOf(2, 1, 1),
            intArrayOf(1, 1, 0),
            intArrayOf(0, 1, 1)
        )
        assertEquals(4, gBFS.orangesRotting(grid))

        val grid2 = arrayOf(
            intArrayOf(2, 1, 1),
            intArrayOf(0, 1, 1),
            intArrayOf(1, 0, 1)
        )
        assertEquals(-1, gBFS.orangesRotting(grid2))

        val grid3 = arrayOf(
            intArrayOf(0, 2)
        )
        assertEquals(0, gBFS.orangesRotting(grid3))
    }

    @Test
    fun findRotted() {
        val grid = arrayOf(
            intArrayOf(2, 1, 1),
            intArrayOf(1, 0, 1),
            intArrayOf(0, 1, 2)
        )

        val rottedPositions = gBFS.findRotted(grid)
        assertArrayEquals(
            listOf(
                intArrayOf(0, 0),
                intArrayOf(2, 2)
            ).toTypedArray(),
            rottedPositions.toTypedArray()
        )
    }
}