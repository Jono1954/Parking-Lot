fun main(args: Array<String>) {
    val input = readLine()!!.split(" ").map { it.toInt() }
    val a = input[0]
    val b = input[1]

    if (b == 0) {
        println("Division by zero, please fix the second argument!")
    } else {
        println(a / b)
    }
}