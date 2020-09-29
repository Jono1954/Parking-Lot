fun main() {
    val rooms = readLine()!!.toInt()
    val price = readLine()!!.toInt().coerceIn(0, 1_000_000)

    val house = when (rooms) {
        in Int.MIN_VALUE..1 -> TypeOfHouse("Cabin", rooms, price, 1.0F)
        in 2..3 -> TypeOfHouse("Bungalow", rooms, price, 1.2F)
        4 -> TypeOfHouse("Cottage", rooms, price, 1.25F)
        in 5..7 -> TypeOfHouse("Mansion", rooms, price, 1.4F)
        else -> TypeOfHouse("Palace", rooms, price, 1.6F)
    }
    println(totalPrice(house))
}

fun totalPrice(house: House) = house.basePrice

open class House(rooms: Int, private val price: Int, private val coefficient: Float = 0.0F) {
    val basePrice: Int
        get() = (price * coefficient).toInt()
}

class TypeOfHouse(houseType: String, rooms: Int, price: Int, coefficient: Float) : House(rooms, price, coefficient)