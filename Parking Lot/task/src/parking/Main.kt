package parking
import kotlin.system.exitProcess

var spots = mutableMapOf<Int, String>()
var maxSpots = 0

fun main() {
  start()
  input()
}

open class Car {
    var num: String = ""
    var color: String = ""

}
fun park (car: Car ) {


for (i in 1..maxSpots) {
    if (spots[i] == "free") {
        spots[i] = "${car.num} ${car.color}"
        println("${car.color} car parked in spot $i.")
        input()


    } else if (spots[i] != "free") { continue }
    println("Sorry, the parking lot is full.")
    input()
}

}
fun leave(spot: Int) {

    if (spots[spot] == "free" ) {
        println("There is no car in spot 1.")
        input()
    } else {
        println("Spot $spot is free.")
        spots[spot] = "free"
        input()
    }

}
fun input() {

    val input = readln().split(" ")

    if (input.first() == "exit") {
        exitProcess(0)
    }


        when (input.first()) {

            "park" -> {
                val car1 = Car()
                car1.num = input[1]
                car1.color = input[2].lowercase()
                park(car1)
            }
            "leave" -> {
                val leaveSpot = input[1].toInt()
                leave(leaveSpot)
            }
            "status" -> {
             if (spots.isEmpty()) {
                println("Parking lot is empty.")
                input()

            }

                for (i in spots.keys) {
                    if (spots[i] != "free") {
                        println("$i ${spots[i]}")
                    }

                }
                input()


            }
            "create" -> {
                spots.clear()
                maxSpots = input[1].toInt()
                for (i in 1..input[1].toInt()) {
                    spots.put(i, "free")
                }
                println("Created a parking lot with ${input[1]} spots.")
                input()

            }
            "reg_by_color" -> checkColor(input[1])
            "spot_by_color" -> spotColor(input[1])
            "spot_by_reg" -> checkReg(input[1])
            else -> input()
        }

}

fun checkReg(reg: String) {
    val regList = mutableListOf<String>()
    for (i in spots.keys) {
        if (spots[i].toString().contains(reg)) {
            regList.add(i.toString())
            continue
        }

    }
    if (regList.isEmpty()) {
        println("No cars with registration number $reg were found.")
        input()
    } else {
        println(regList.toString().replace("[", "").replace("]", ""))
        input()
    }
}

fun spotColor(color: String) {
    val colorList = mutableListOf<String>()
    for (i in spots.keys) {
        if (spots[i].toString().contains(color.lowercase())) {
            colorList.add(i.toString())
            continue
        }

    }
    if (colorList.isEmpty()) {
        println("No cars with color $color were found.")
        input()
    } else {
        println(colorList.toString().replace("[", "").replace("]", ""))
        input()
    }
}

fun checkColor(color: String) {
    color.lowercase()
    val colorList = mutableListOf<String>()

    for (i in spots.values) {
        if (color.lowercase() in i) {
            colorList.add(i.substringBefore(" "))
            continue
        }

    }
    if (colorList.isEmpty()) {
        println("No cars with color $color were found.")
        input()
    } else {

        println(colorList.toString().replace("[", "").replace("]", ""))
        input()
    }
}

fun start() {
    val input1 = readln().split(" ")
    if (input1.first() == "exit") {
        exitProcess(0)
    }
    if (input1.first() != "create") {
        println("Sorry, a parking lot has not been created.")
        start()
    } else {
        maxSpots = input1[1].toInt()
        for (i in 1..input1[1].toInt()) {
            spots.put(i, "free")
        }
    }
    println("Created a parking lot with ${input1[1]} spots.")
    input()

}