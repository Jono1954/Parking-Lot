// Simplest solution to reversing an integer number

fun reverse(input: Int?): Int {
    return input?.toString()?.reversed()?.toInt() ?: -1
}


// Another solution using integer division and modulus - thanks to User 19784131 :)

fun reverse(input: Int?): Int {

    if (input == null) {
        return -1
    }
    var number: Int = input
    var reverse = 0
    while (number != 0) {
        reverse = reverse * 10 + number % 10
        number /= 10
    }
    return reverse
}



// Another solution using pop & push on a stack - thanks to Danny :)

import java.util.*

fun reverse(input: Int?): Int {
    if (input == null) return -1
    val stack: Stack<Char> = Stack()
    input.toString().forEach { char ->
        stack.push(char)
    }
    var reverse = ""
    repeat(stack.size) {
        reverse += stack.pop()
    }
    return reverse.toInt()
}