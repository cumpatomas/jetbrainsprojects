fun main() {
    val productType = readln()
    val price = readln().toInt()
    val product = Product(price)
    println(totalPrice(product, productType))
}

class Product(val price: Int) {

}

fun totalPrice(product: Product, type: String): Int {
    var tax = 0.0
    when (type) {
        "headphones" -> tax = 0.11
        "smartphone" -> tax = 0.15
        "tv" -> tax = 0.17
        "laptop" -> tax = 0.19


    }
    val total = product.price.toDouble() + product.price.toDouble() * tax
    return total.toInt()
}
