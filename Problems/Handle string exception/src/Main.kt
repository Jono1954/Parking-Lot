fun main() {
    val index = readLine()!!.toInt()
    val word = readLine()!!

    val errMsg = "There isn't such an element in the given string, please fix the index!"
    if (index in word.indices) {
        println(word[index])
    } else {
        println(errMsg)
    }
}