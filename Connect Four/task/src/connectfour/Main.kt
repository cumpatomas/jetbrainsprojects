package connectfour

import kotlin.system.exitProcess

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

// creation of the board's MutableList all items are "| "
    var boardList = mutableListOf<String>()
    repeat (rows * cols) {
            boardList.add("| ")
    }

    var turn1 = true  // if true, is name1's turn if false, is name2's turn

    printBoard(cols, rows, 0, turn1, name1, name2, boardList)

}

private fun turns(
    turn1f: Boolean,
    name1: String,
    name2: String,
    cols: Int,
    rows: Int,
    boardList: MutableList<String>
): Int {

    var boardListTurn = boardList
    var turn1 = turn1f

    if (turn1 == true) {
        println("$name1's turn:")
        turn1 = false
    } else {
        println("$name2's turn:")
        turn1 = true
    }
    val chCol = readLine()!!
    val regex = Regex("\\d+")
    if (chCol == "end") {
        println("Game over!")
        exitProcess(0)
    } else if (!chCol.matches(regex)) {
        println("Incorrect column number")
        turns(!turn1, name1, name2, cols, rows, boardListTurn)
    } else if (chCol.toInt() !in 1..cols) {
        println("The column number is out of range (1 - $cols)")
        turns(!turn1, name1, name2, cols, rows, boardListTurn)
    } else {
        printBoard(cols, rows, chCol.toInt(), turn1, name1, name2, boardListTurn)
    }
    return chCol.toInt()
}

private fun printBoard(
    cols: Int,
    rows: Int,
    chColFun: Int,
    turn: Boolean,
    name1f: String,
    name2f: String,
    boardList: MutableList<String>
) {
    var boardListf = boardList
    var turnf = turn

    // modify board with the input
    if (chColFun != 0) {

        for (i in boardListf.lastIndex downTo cols - 1 step cols) {
            if (boardListf[i - (cols - chColFun)] == "| ") {
                boardListf.removeAt(i - (cols - chColFun))
                when (turnf) {
                    false -> {

                        boardListf.add(i - (cols - chColFun), "|o")
                        break
                    }
                    else -> {

                        boardListf.add(i - (cols - chColFun), "|*")
                        break
                    }
                }
            } else if (boardListf[i - (cols - chColFun)] != "| " && i == cols -1){
                println("Column $chColFun is full")
                turns(!turnf, name1f, name2f, cols, rows, boardListf)
                return
            } else {
                continue

            }
        }

    }

    // print the columns

    for (i in 1..cols) {
        print(" $i")
        if (i == cols) {
            println("")
        }
    }
//print the board

    for (i in 0..boardListf.lastIndex - (cols - 1) step cols) {
        val line = boardListf.subList(i, i + cols).joinToString().replace(", ", "") + ("|")
        println(line)
    }

       //print the bottom line
   for (i in 1..cols) {
       print("==")
       if (i == cols) {
           print("=")
           if (i == cols) {
               println("")
           }
       }
   }
    turns(turnf, name1f, name2f, cols, rows, boardListf)

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