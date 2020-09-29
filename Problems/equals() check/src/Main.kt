import java.util.Scanner

// My solution:

// Two data class objects by default are defined as equal if ALL the properties defined
// in the primary constructor are equal.  To change this equality logic, override
// the 'fun equals()' method and test equality using only the 'name' and 'age' properties
// and exclude testing the 'balance' property

data class Client(val name: String, val age: Int, val balance: Int) {
    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        
        other as Client
        
        if (name != other.name) return false
        if (age != other.age) return false
        
        // Exclude this property in the equality check
        // if (balance != other.balance) return false
        
        return true
    }
}

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    
    val (name1, age1, balance1) = input.nextLine().split(" ")
    val (name2, age2, balance2) = input.nextLine().split(" ")
    
    val client1 = Client(name1, age1.toInt(), balance1.toInt())
    val client2 = Client(name2, age2.toInt(), balance2.toInt())
    
    println(client1.equals(client2))
}