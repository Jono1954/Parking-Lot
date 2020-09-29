fun parseCardNumber(cardNumber: String): Long {
    if (cardNumber.length != 19) {
        throw Exception("Incorrect format, should be 1111 2222 3333 4444. Please check your input")
    }

    for (index in cardNumber.indices) {
        if (cardNumber[index] !in "0123456789 ") {
            throw Exception("Can only input numbers from 0 to 9 and spaces")
        }
    }

    for (index in listOf(4, 9, 14)) {
        if (!cardNumber[index].isWhitespace()) {
            throw Exception("Spaces are in the wrong places")
        }
    }

    for (index in cardNumber.indices) {
        if (index in 0..3 && !cardNumber[index].isDigit()) {
            throw Exception("Error in first 4 digits. Must be numbers 0 to 9")
        } else if (index in 5..8 && !cardNumber[index].isDigit()) {
            throw Exception("Error in second 4 digits. Must be numbers 0 to 9")
        } else if (index in 10..13 && !cardNumber[index].isDigit()) {
            throw Exception("Error in third 4 digits. Must be numbers 0 to 9")
        } else if (index in 15..18 && !cardNumber[index].isDigit()) {
            throw Exception("Error in fourth 4 digits. Must be numbers 0 to 9")
        }
    }

    return cardNumber.filterNot { it == ' ' }.toLong()
}