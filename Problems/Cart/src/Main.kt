// Another solution = thanks to Michael Duffy

import kotlin.system.exitProcess

fun main() {
    val productType = readLine()!!
    val basePrice = readLine()!!.toInt().coerceIn(0, 1_000_000)

    val product = when (productType) {
        "headphones" -> TaxableProduct(productType, basePrice, 11)
        "smartphone" -> TaxableProduct(productType, basePrice, 15)
        "tv" -> TaxableProduct(productType, basePrice, 17)
        "laptop" -> TaxableProduct(productType, basePrice, 19)
        else -> exitProcess(-1)
    }
    println(totalPrice(product))
}

fun totalPrice(product: Product): Int = product.price

open class Product(private val basePrice: Int, private val taxRate: Int = 0) {
    val price: Int
        get() = basePrice + basePrice * taxRate / 100
}

class TaxableProduct(val productType: String, basePrice: Int, taxRate: Int) : Product(basePrice, taxRate)