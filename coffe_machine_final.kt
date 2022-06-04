package machine

import kotlin.system.exitProcess

fun main() {
    val machine = CoffeMachine()
    machine.menu()
    machine.input(readln())

}

class CoffeMachine {

    enum class STATE(val mge: String) {
        CHOOSE("Write action (buy, fill, take, remaining, exit):"),
        REMAINING(""),
        BUY("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:"),
        FILL(""),
        TAKE("");

    }

    var water = 400
    var milk = 540
    var coffee = 120
    var cups = 9
    var money = 550

    companion object {
        var state = STATE.CHOOSE

    }

    fun menu() {
        if (state == STATE.CHOOSE) {
            println(STATE.CHOOSE.mge)
        }
        if (state == STATE.BUY) {
            println(STATE.BUY.mge)
        }
    }

    fun input(input: String) {
        when (input) {
            "buy" -> {
                state = STATE.BUY
                menu()
                input(readln())
            }
            "1" -> {


                if (cups > 0 && water >= Espresso().water && milk >= Espresso().milk && coffee >= Espresso().beans) {
                    println("I have enough resources, making you a coffee!")
                    println()
                    water -= Espresso().water
                    milk -= Espresso().milk
                    coffee -= Espresso().beans
                    state = STATE.CHOOSE
                    cups --
                    money += Espresso().cost
                    menu()
                    input(readln())

                } else {

                    if (water < Espresso().water) {
                        state = STATE.CHOOSE
                        println("Sorry, not enough water!")
                        println()
                        menu()
                        input(readln())
                    }
                    if (milk < Espresso().milk) {
                        state = STATE.CHOOSE
                        println("Sorry, not enough milk!")
                        println()
                        menu()
                        input(readln())
                    }
                    if (coffee < Espresso().beans) {
                        state = STATE.CHOOSE
                        println("Sorry, not enough coffee!")
                        println()
                        menu()
                        input(readln())
                    }

                }

            }
            "2" -> {

                if (cups > 0 && water >= Latte().water && milk >= Latte().milk && coffee >= Latte().beans) {
                    println("I have enough resources, making you a coffee!")
                    println()
                    water -= Latte().water
                    milk -= Latte().milk
                    coffee -= Latte().beans
                    state = STATE.CHOOSE
                    cups --
                    money += Latte().cost
                    menu()
                    input(readln())

                } else {

                    if (water < Latte().water) {
                        state = STATE.CHOOSE
                        println("Sorry, not enough water!")
                        println()
                        menu()
                        input(readln())
                    }
                    if (milk < Latte().milk) {
                        state = STATE.CHOOSE
                        println("Sorry, not enough milk!")
                        println()
                        menu()
                        input(readln())
                    }
                    if (coffee < Latte().beans) {
                        state = STATE.CHOOSE
                        println("Sorry, not enough coffee!")
                        println()
                        menu()
                        input(readln())
                    }

                }

            }

            "3" -> {

                if (cups > 0 && water >= Capp().water && milk >= Capp().milk && coffee >= Capp().beans) {
                    println("I have enough resources, making you a coffee!")
                    println()
                    water -= Capp().water
                    milk -= Capp().milk
                    coffee -= Capp().beans
                    state = STATE.CHOOSE
                    cups --
                    money += Capp().cost
                    menu()
                    input(readln())

                } else {

                    if (water < Capp().water) {
                        state = STATE.CHOOSE
                        println("Sorry, not enough water!")
                        println()
                        menu()
                        input(readln())
                    }
                    if (milk < Capp().milk) {
                        state = STATE.CHOOSE
                        println("Sorry, not enough milk!")
                        println()
                        menu()
                        input(readln())
                    }
                    if (coffee < Capp().beans) {
                        state = STATE.CHOOSE
                        println("Sorry, not enough coffee!")
                        println()
                        menu()
                        input(readln())
                    }

                }

            }

            "back" -> {
                state = STATE.CHOOSE
                menu()
                input(readln())
            }
            "remaining" -> {
                println(
                    """
                The coffee machine has:
                $water of water
                $milk of milk
                $coffee of coffee beans
                $cups of disposable cups
                $$money of money
            """.trimIndent()
                )
                println()
                menu()
                input(readln())
            }
            "fill" -> {
                println("Write how many ml of water do you want to add:")
                water += readln().toInt()
                println("Write how many ml of milk do you want to add:")
                milk += readln().toInt()
                println("Write how many grams of coffee beans do you want to add:")
                coffee += readln().toInt()
                println("Write how many disposable cups of coffee do you want to add:")
                cups += readln().toInt()
                println()
                menu()
                input(readln())
            }
            "exit" -> exitProcess(0)
            "take" -> { println("I gave you $$money")
                money = 0
                println()
                menu()
                input(readln())
            }

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
}



