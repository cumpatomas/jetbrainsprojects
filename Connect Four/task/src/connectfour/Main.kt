package connectfour

fun main() {
    println("Connect Four")
    println("First player's name:")
    val name1 = readLine()!!
    println("Second player's name:")
    val name2 = readLine()!!
    println("""
        Set the board dimensions (Rows x Columns)
        Press Enter for default (6 x 7)
    """.trimIndent())

    val (rows, cols) = sizeInput()

    println("$name1 VS $name2")
    println("$rows X $cols board")
    printBoard(cols, rows, 0)
    var turn1 = true  // if true is name1's turn, if false is name2's turn

 // comienzo de la funcion turns()
    if (turn1) {
        println("$name1's turn:")
        turn1 = false
    } else {
        println("$name2's turn:")
    }
    val chCol = readLine()!!
    if (!chCol[0].isDigit()) {
        println("The column number is out of range (1 - $cols")
        //agregar return a la funcion
    } else if (chCol.toInt() !in 1..cols) {
        println("The column number is out of range (1 - $cols")
        //agregar return a la funcion
    } else {

    }

}

private fun printBoard(cols: Int, rows: Int, chCol: Int) {
    //print the board
    for (i in 1..cols) {
        print(" $i")
        if (i == cols) {
            println("")
        }
    }

    for (i in 1..rows) {
        for (j in 1..cols + 1) {
            print("| ")
            if (j == cols + 1) {
                println("")
            }
        }
    }
    for (i in 1..cols) {
        print("==")
        if (i == cols) {
            print("=")
            if (i == cols) {
                println("")
            }
        }
    }
}

fun sizeInput(): Pair<Int, Int> {

    val checkIn = Regex("\\d+ ?x?X? ?\\d+")
    val sizeIn = readLine()!!.replace(" ", "").replace("\t", "")
    var rows = 0
    var cols = 0
    if (sizeIn.matches(checkIn)) {
        if (sizeIn.first().toString().toInt() !in 5..9) {
            println("Board rows should be from 5 to 9")
            println("""
        Set the board dimensions (Rows x Columns)
        Press Enter for default (6 x 7)
    """.trimIndent())
            return sizeInput()
        } else if (sizeIn.last().toString().toInt() !in 5..9) {
            println("Board columns should be from 5 to 9")
            println("""
        Set the board dimensions (Rows x Columns)
        Press Enter for default (6 x 7)
    """.trimIndent())
            return sizeInput()
        } else {
            rows = sizeIn.first().toString().toInt()
            cols = sizeIn.last().toString().toInt()
        }

    } else if (sizeIn.isEmpty()) {
        rows = 6
        cols = 7

    } else if (!sizeIn.matches(checkIn)) {
        println("Invalid input")
        println("""
        Set the board dimensions (Rows x Columns)
        Press Enter for default (6 x 7)
    """.trimIndent())
        return sizeInput()
    }

    return Pair(rows, cols)
}