package machine

import kotlin.system.exitProcess

fun main() {
    var water = 400
    var milk = 540
    var coffee = 120
    var cups = 9
    var money = 550

    options(water, milk, coffee, money, cups)

}

private fun state(
    water: Int,
    milk: Int,
    coffee: Int,
    cups: Int,
    money: Int
) {
        println(
            """ 
The coffee machine has:
$water ml of water
$milk ml of milk
$coffee g of coffee beans
$cups disposable cups
$$money of money  

       """.trimIndent()
        )
  }

private fun options(
    water: Int,
    milk: Int,
    coffee: Int,
    money: Int,
    cups: Int,
) {
    var water1 = water
    var milk1 = milk
    var coffee1 = coffee
    var money1 = money
    var cups1 = cups
    println("Write action (buy, fill, take):")
    val option = readln()!!
    when (option) {
        "buy" -> {
            val buy = buy(water1, milk1, coffee1, money1, cups1)
            when (buy) {
                Pair("1", true) -> {

                    water1 -= Espresso().water
                    milk1 -= Espresso().milk
                    coffee1 -= Espresso().beans
                    money1 += Espresso().cost
                    cups1--
                    options(water1, milk1, coffee1, money1, cups1)


                }
                Pair("2", true) -> {

                    water1 -= Latte().water
                    milk1 -= Latte().milk
                    coffee1 -= Latte().beans
                    money1 += Latte().cost
                    cups1--
                    options(water1, milk1, coffee1, money1, cups1)

                }
                Pair("3", true) -> {

                    water1 -= Capp().water
                    milk1 -= Capp().milk
                    coffee1 -= Capp().beans
                    money1 += Capp().cost
                    cups1--
                    options(water1, milk1, coffee1, money1, cups1)

                }
                else -> options(water1, milk1, coffee1, money1, cups1)

            }
        }
        "fill" -> {
            println("Write how many ml of water do you want to add:")
            water1 += readln()!!.toInt()
            println("Write how many ml of milk do you want to add:")
            milk1 += readln()!!.toInt()
            println("Write how many grams of coffee beans do you want to add:")
            coffee1 += readln()!!.toInt()
            println("Write how many disposable cups of coffee do you want to add:")
            cups1 += readln()!!.toInt()
            options(water1, milk1, coffee1, money1, cups1)

        }
        "take" -> {
            println("I gave you $money1")
            println()
            money1 = 0
            options(water1, milk1, coffee1, money1, cups1)

        }
        "remaining" -> {
            state(water1, milk1, coffee1, cups1, money1)
            options(water1, milk1, coffee1, money1, cups1)

        } else -> exitProcess(0)

    }
}


abstract class Coffee() {
    abstract val water: Int
    abstract val beans: Int
    abstract val milk: Int
    abstract val cost: Int
}

class Espresso() : Coffee() {
    override val water = 250
    override val beans = 16
    override val milk = 0
    override val cost = 4

}

class Latte() : Coffee() {
    override val water = 350
    override val beans = 20
    override val milk = 75
    override val cost = 7

}

class Capp() : Coffee() {
    override val water = 200
    override val beans = 12
    override val milk = 100
    override val cost = 6

}

fun buy(w: Int, m: Int, b: Int, money: Int, cups: Int): Pair<String, Boolean> {

    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
    val option = readln()!!
    var possible = false

    if (option == "1") {
        if (w >= Espresso().water && m >= Espresso().milk && b >= Espresso().beans) {
            println("I have enough resources, making you a coffee!")
            println()
            possible = true
            return Pair(option, possible)
        }

    } else if (option == "2") {
        if (w >= Latte().water && m >= Latte().milk && b >= Latte().beans) {
            println("I have enough resources, making you a coffee!")
            println()
            possible = true
            return Pair(option, possible)
        }
    } else if (option == "3") {
        if (w >= Capp().water && m >= Capp().milk && b >= Capp().beans) {
            possible = true
            println("I have enough resources, making you a coffee!")
            println()
            return Pair(option, possible)
        }
    } else if (option == "back") { options(w, m, b, money, cups) }

    if (w < Capp().water || w < Latte().water || w < Espresso().water) {
        println("Sorry, not enough water!")
    }
    if (m < Capp().milk || m < Latte().milk || m < Espresso().milk) {
        println("Sorry, not enough milk!")
    }
    if (b < Capp().beans || b < Latte().beans || b < Espresso().beans) {
        println("Sorry, not enough coffee!")
    }
    return Pair(option, possible)

}


