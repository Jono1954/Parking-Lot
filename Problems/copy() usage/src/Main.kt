// Another solution - thanks to Terrain :)
// Using Elvis operator to test whether input is null

import java.util.Scanner

// do not change this data class
data class Box(val height: Int, val length: Int, val width: Int)

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    
    // Using Elvis operator to test whether input is missing (= null)
    val (h, l1, l2, w) =
            input.nextLine().split(" ").map { it.toInt() } ?: return
    
    Box(h, l1, w).apply(::println).copy(length = l2).let(::println)
}