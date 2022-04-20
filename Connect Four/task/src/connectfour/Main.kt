package connectfour

import java.lang.Exception
import kotlin.system.exitProcess

fun main() {

    println("Connect Four")
    println("First player's name:")
    val name1 = readLine()!!
    println("Second player's name:")
    val name2 = readLine()!!

    println(
        """
        Set the board dimensions (Rows x Columns)
        Press Enter for default (6 x 7)
    """.trimIndent()
    )
    val (rows, cols) = sizeInput()

    // creation of the board's MutableList all items are "| "
    val boardList = mutableListOf<String>()
    repeat(rows * cols) {
        boardList.add("| ")
    }

    val turn1 = true  // if true, is name1's turn if false, is name2's turn
    var numOfGames = numberGames()

    if (numOfGames == "single") {
        println("$name1 VS $name2")
        println("$rows X $cols board")
        println("Single game")
        printBoard(cols, rows, 0, turn1, name1, name2, boardList)
    } else {
        println("$name1 VS $name2")
        println("$rows X $cols board")
        println("Total $numOfGames games")
        multiBoard(1, numOfGames.toInt(), cols, rows, 0, turn1, name1, name2, boardList)
    }

}
// The multi game function
private fun multiBoard(

    game: Int,
    maxGames: Int,
    cols: Int,
    rows: Int,
    chColFun: Int,
    turn: Boolean,
    name1f: String,
    name2f: String,
    boardList: MutableList<String>,
    newGame: Boolean = true,
    point1: Int = 0,
    point2: Int = 0
) {
    var boardListf = boardList
    var turnf = turn
    var _game = game
    var p1Points = point1
    var p2Points = point2
    var _newGame = newGame

    if (game > maxGames) {

        println("Game Over!")
        exitProcess(0)
    }

    if (newGame) println("Game #$_game")


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
            } else if (boardListf[i - (cols - chColFun)] != "| " && i == cols - 1) {
                println("Column $chColFun is full")
                turns(game, maxGames, true, turnf, name1f, name2f, cols, rows, boardListf, p1Points, p2Points)
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
    val (oWinner, xWinner) = checkBoard(cols, boardListf, rows)

    if (game > maxGames) {

        if (oWinner) {
            println("Player $name1f won")
            println("Game Over!")
            exitProcess(0)
        } else if (xWinner) {
            println("Player $name2f won")
            println("Game Over!")
            exitProcess(0)
        } else if ("| " !in boardList) {
            println("It is a draw")
            println("Game Over!")
            exitProcess(0)

        }

    } else {
        if (oWinner) {
            p1Points += 2
            println("Player $name1f won")
            println("Score")
            println("$name1f: $p1Points $name2f: $p2Points")
            if (_game <= maxGames) {
                _game++
                boardListf.clear()
                repeat(rows * cols) {
                    boardListf.add("| ")
                }
                multiBoard(_game, maxGames, cols, rows, 0, turnf, name1f, name2f, boardListf, true, p1Points, p2Points)
            } else {
                println("Game Over!")
                exitProcess(0)
            }
        } else if (xWinner) {
            p2Points += 2
            println("Player $name2f won")
            println("Score")
            println("$name1f: $p1Points $name2f: $p2Points")
            if (_game <= maxGames) {
                _game++
                boardListf.clear()
                repeat(rows * cols) {
                    boardListf.add("| ")
                }
                multiBoard(_game, maxGames, cols, rows, 0, turnf, name1f, name2f, boardListf, true, p1Points, p2Points)
            } else {
                println("Game Over!")
                exitProcess(0)
            }

        } else if ("| " !in boardList) {
            println("It is a draw")
            p1Points += 1
            p2Points += 1
            println("Score")
            println("$name1f: $p1Points $name2f: $p2Points")

            if (_game <= maxGames) {
                _game++
                boardListf.clear()
                repeat(rows * cols) {
                    boardListf.add("| ")
                }
                multiBoard(_game, maxGames, cols, rows, 0, turnf, name1f, name2f, boardListf, true, p1Points, p2Points)
            } else {
                println("Game Over!")
                exitProcess(0)
            }


        } else {

            turns(game, maxGames, true, turnf, name1f, name2f, cols, rows, boardListf, p1Points, p2Points)
        }
    }

}

private fun numberGames(): String {
    println(
        """
        Do you want to play single or multiple games?
        For a single game, input 1 or press Enter
        Input a number of games:
    """.trimIndent()
    )
    var numOfGames = readLine()!!

    when {
        numOfGames.isNullOrEmpty() -> return "single"
        numOfGames.isNullOrBlank() -> return "single"
    }
    try {
        numOfGames.toInt()
    } catch (e: Exception) {
        println("Invalid input")
        return numberGames()
    }
    when {
        numOfGames.toInt() == 1 -> numOfGames = "single"
        numOfGames.toInt() < 1 -> {
            println("Invalid input"); return numberGames()
        }
        numOfGames.toInt() > 1 -> numOfGames
        else -> {
            println("Invalid input"); return numberGames()
        }
    }
    return numOfGames
}

private fun turns(
    game: Int,
    maxGame: Int,
    multi: Boolean,
    turn1f: Boolean,
    name1: String,
    name2: String,
    cols: Int,
    rows: Int,
    boardList: MutableList<String>,
    point1: Int = 0,
    point2: Int = 0
): Int {

    val boardListTurn = boardList
    var turn1 = turn1f

    if (turn1) {
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
        turns(game, maxGame, multi, !turn1, name1, name2, cols, rows, boardListTurn, point1, point2)
    } else if (chCol.toInt() !in 1..cols) {
        println("The column number is out of range (1 - $cols)")
        turns(game, maxGame, multi, !turn1, name1, name2, cols, rows, boardListTurn, point1, point2)
    } else {
        if (multi) {
            multiBoard(game, maxGame, cols, rows, chCol.toInt(), turn1, name1, name2, boardList, false, point1, point2)
        } else {
            printBoard(cols, rows, chCol.toInt(), turn1, name1, name2, boardListTurn)
        }


    }
    return chCol.toInt()
}
// The single game function
private fun printBoard(
    cols: Int,
    rows: Int,
    chColFun: Int,
    turn: Boolean,
    name1f: String,
    name2f: String,
    boardList: MutableList<String>,
) {
    val boardListf = boardList
    val turnf = turn


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
            } else if (boardListf[i - (cols - chColFun)] != "| " && i == cols - 1) {
                println("Column $chColFun is full")
                turns(0, 0, false, !turnf, name1f, name2f, cols, rows, boardListf)
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
    val (oWinner, xWinner) = checkBoard(cols, boardListf, rows)


    if (oWinner) {
        println("Player $name1f won")
        println("Game Over!")
        exitProcess(0)
    } else if (xWinner) {
        println("Player $name2f won")
        println("Game Over!")
        exitProcess(0)
    } else if ("| " !in boardList) {
        println("It is a draw")
        println("Game Over!")
        exitProcess(0)

    } else {
        turns(0, 0, false, turnf, name1f, name2f, cols, rows, boardListf)
    }

}

private fun checkBoard(
    cols: Int,
    boardListf: MutableList<String>,
    rows: Int
): Pair<Boolean, Boolean> {
    var counto: Int
    var countx: Int
    var oWinner = false
    var xWinner = false

    // check DIAGONALS 1st half
    loop@ for (i in 0..cols - 3) {

        for (h in boardListf.lastIndex downTo cols step cols * 3) {
            countx = 0
            counto = 0

            for (j in i..h step cols + 1) {

                if (boardListf[j] == "|o") {
                    countx = 0
                    counto++
                    if (counto == 4) {
                        oWinner = true
                        break@loop
                    }
                } else if (boardListf[j] == "|*") {
                    counto = 0
                    countx++
                    if (countx == 4) {
                        xWinner = true
                        break@loop

                    }
                } else {
                    countx = 0
                    counto = 0
                }
            }
        }
    }
    // check DIAGONALS 2nd half
    loop@ for (i in cols..(cols * rows - 1) - (cols * 3)) {

        for (h in boardListf.lastIndex - 1 downTo boardListf.lastIndex - 3) {
            countx = 0
            counto = 0

            for (j in i..h step cols + 1) {

                if (boardListf[j] == "|o") {
                    countx = 0
                    counto++
                    if (counto == 4) {
                        oWinner = true
                        break@loop
                    }
                } else if (boardListf[j] == "|*") {
                    counto = 0
                    countx++
                    if (countx == 4) {
                        xWinner = true
                        break@loop

                    }
                } else {
                    countx = 0
                    counto = 0
                }
            }
        }
    }
// check DIAGONALS 3rd half
    loop@ for (i in cols - 1..boardListf.lastIndex - (cols * 3)) {

        for (h in cols * (rows - 1)..boardListf.lastIndex - 3) {
            countx = 0
            counto = 0

            for (j in i..h step cols - 1) {
                if (boardListf[j] == "|o") {
                    countx = 0
                    counto++
                    if (counto == 4) {
                        oWinner = true
                        break@loop
                    }
                } else if (boardListf[j] == "|*") {
                    counto = 0
                    countx++
                    if (countx == 4) {
                        xWinner = true
                        break@loop

                    }
                } else {
                    countx = 0
                    counto = 0
                }
            }
        }
    }
// check DIAGONALS 4th half
    loop@ for (i in cols - 2 downTo 3) {


        for (h in cols * (rows - 2) downTo cols * 3) {
            countx = 0
            counto = 0

            for (j in i..h step cols - 1) {
                if (boardListf[j] == "|o") {
                    countx = 0
                    counto++
                    if (counto == 4) {
                        oWinner = true
                        break@loop
                    }
                } else if (boardListf[j] == "|*") {
                    counto = 0
                    countx++
                    if (countx == 4) {
                        xWinner = true
                        break@loop

                    }
                } else {
                    countx = 0
                    counto = 0
                }
            }
        }
    }

// check HORIZONTALS
    loop@ for (i in 0..boardListf.lastIndex - (cols - 1) step cols) {


        for (h in cols - 1..boardListf.lastIndex step cols) {
            countx = 0
            counto = 0

            for (j in i..h) {
                if (boardListf[j] == "|o") {
                    countx = 0
                    counto++
                    if (counto == 4) {
                        oWinner = true
                        break@loop
                    }
                } else if (boardListf[j] == "|*") {
                    counto = 0
                    countx++
                    if (countx == 4) {
                        xWinner = true
                        break@loop

                    }
                } else {
                    countx = 0
                    counto = 0
                }
            }
        }
    }

// check VERTICALS
    loop@ for (i in 0 until cols) {


        for (h in boardListf.lastIndex - (cols - 1)..boardListf.lastIndex) {
            countx = 0
            counto = 0

            for (j in i..h step cols) {
                if (boardListf[j] == "|o") {
                    countx = 0
                    counto++
                    if (counto == 4) {
                        oWinner = true
                        break@loop
                    }
                } else if (boardListf[j] == "|*") {
                    counto = 0
                    countx++
                    if (countx == 4) {
                        xWinner = true
                        break@loop

                    }
                } else {
                    countx = 0
                    counto = 0
                }
            }
        }
    }
    return Pair(oWinner, xWinner)
}

fun sizeInput(): Pair<Int, Int> {

    val checkIn = Regex("\\d+ ?x?X? ?\\d+")
    val sizeIn = readLine()!!.replace(" ", "").replace("\t", "")
    var rows = 0
    var cols = 0
    if (sizeIn.matches(checkIn)) {
        if (sizeIn.first().toString().toInt() !in 5..9) {
            println("Board rows should be from 5 to 9")
            println(
                """
        Set the board dimensions (Rows x Columns)
        Press Enter for default (6 x 7)
    """.trimIndent()
            )
            return sizeInput()
        } else if (sizeIn.last().toString().toInt() !in 5..9) {
            println("Board columns should be from 5 to 9")
            println(
                """
        Set the board dimensions (Rows x Columns)
        Press Enter for default (6 x 7)
    """.trimIndent()
            )
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
        println(
            """
        Set the board dimensions (Rows x Columns)
        Press Enter for default (6 x 7)
    """.trimIndent()
        )
        return sizeInput()
    }

    return Pair(rows, cols)
}
