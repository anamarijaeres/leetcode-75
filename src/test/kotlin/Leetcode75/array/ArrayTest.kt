package Leetcode75.array


import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ArrayTest {

    @Test
    fun mergeAlternately() {
        val array = Array()
        assertEquals("apbqcr", array.mergeAlternately("abc", "pqr"))
        assertEquals("agbhci", array.mergeAlternately("abc", "ghi"))
        assertEquals("abcd", array.mergeAlternately("abcd", ""))
        assertEquals("pqr", array.mergeAlternately("", "pqr"))
    }

    @Test
    fun gcdOfStrings() {
        val array = Array()
        assertEquals("ABC", array.gcdOfStrings("ABCABC", "ABC"))
        assertEquals("ABC", array.gcdOfStrings("ABC", "ABCABC"))
        assertEquals("", array.gcdOfStrings("ABC", "DEF"))
        assertEquals("", array.gcdOfStrings("AB", "CD"))
    }

    @Test
    fun kidsWithCandies() {
        val array = Array()
        assertArrayEquals(
            booleanArrayOf(true, true, true, false, true).toTypedArray(),
            array.kidsWithCandies(intArrayOf(2, 3, 5, 1, 3), 3).toTypedArray()
        )
        assertArrayEquals(
            booleanArrayOf(true, false, false, false, false).toTypedArray(),
            array.kidsWithCandies(intArrayOf(4, 2, 1, 1, 2), 1).toTypedArray()
        )
        assertArrayEquals(
            booleanArrayOf(true, false, true).toTypedArray(),
            array.kidsWithCandies(intArrayOf(12, 1, 12), 10).toTypedArray()
        )
    }

    @Test
    fun canPlaceFlowers() {
        val array = Array()
        assertTrue(array.canPlaceFlowers(intArrayOf(1, 0, 0, 0, 1), 1))
        assertFalse(array.canPlaceFlowers(intArrayOf(1, 0, 0, 0, 1), 2))
        assertTrue(array.canPlaceFlowers(intArrayOf(1, 0, 0, 0, 1, 0, 0), 2))
        assertTrue(array.canPlaceFlowers(intArrayOf(0), 1))
    }

    @Test
    fun reverseVowels() {
        val array = Array()
        assertEquals("holle", array.reverseVowels("hello"))
        assertEquals("leotcede", array.reverseVowels("leetcode"))
        assertEquals("hUllo", array.reverseVowels("hollU"))
        assertEquals("abcd", array.reverseVowels("abcd"))
    }
}