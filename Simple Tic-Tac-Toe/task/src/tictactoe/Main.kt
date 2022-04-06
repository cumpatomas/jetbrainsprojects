package tictactoe
import kotlin.system.exitProcess

fun main () {
    var xTurn = true
    var cells = "         ".toMutableList()
    PrintBoard(cells)
    CoorFun(cells, xTurn)
}

private fun checkResult(cells: MutableList<Char>) {
    val xwin = "XXX"
    val owin = "OOO"
//---Variables of winner lines
    val line1 = "${cells[0]}${cells[1]}${cells[2]}"
    val line2 = "${cells[3]}${cells[4]}${cells[5]}"
    val line3 = "${cells[6]}${cells[7]}${cells[8]}"
    val line4 = "${cells[0]}${cells[3]}${cells[6]}"
    val line5 = "${cells[1]}${cells[4]}${cells[7]}"
    val line6 = "${cells[2]}${cells[5]}${cells[8]}"
    val line7 = "${cells[0]}${cells[4]}${cells[8]}"
    val line8 = "${cells[2]}${cells[4]}${cells[6]}"
//List of winner lines
    val LineList = mutableListOf(line1, line2, line3, line4, line5, line6, line7, line8)
    if (xwin in LineList && owin !in LineList) {
        PrintBoard(cells)
        println("X wins")
        exitProcess(0)
    } else if (owin in LineList && xwin !in LineList) {
        PrintBoard(cells)
        println("O wins")
        exitProcess(0)

    } else if(' ' !in cells) {
        PrintBoard(cells)
        println("Draw")
        exitProcess(0)
    }
}
//----Function to rrint the board
private fun PrintBoard(cells: MutableList<Char>) {
    print(
        """
---------
| ${cells[0]} ${cells[1]} ${cells[2]} |
| ${cells[3]} ${cells[4]} ${cells[5]} |
| ${cells[6]} ${cells[7]} ${cells[8]} |
---------
"""
    )
}

//----Entering Coordinates and checking input
fun CoorFun(cells: MutableList<Char>, xturn: Boolean) {

    print("Enter the coordinates: ")
    val coorList = mutableListOf<String>("11", "12", "13", "21", "22", "23", "31", "32", "33")
    val coor = readLine()!!.replace(" ", "")
    if (!coor[0].isDigit() || !coor[1].isDigit()) {
        println("You should enter numbers!")
        print("Enter the coordinates: ")
        CoorFun(cells, xturn)
    }

    if (coor !in coorList) {
        println("Coordinates should be from 1 to 3!")
        print("Enter the coordinates: ")
        CoorFun(cells, xturn)
    }
    when (coor) {
        "11" -> if (cells[0] != ' ') {
            println("This cell is occupied! Choose another one!")
            CoorFun(cells, xturn)
        } else {
            if (xturn == true) {
                cells[0] = 'X'
                checkResult(cells)
                PrintBoard(cells)
                CoorFun(cells, xturn = false)
            } else {
                cells[0] = 'O'
                checkResult(cells)
                PrintBoard(cells)
                CoorFun(cells, xturn = true)
            }
        }
        "12" -> if (cells[1] != ' ') {
            println("This cell is occupied! Choose another one!")
            CoorFun(cells, xturn)

        } else {
            if (xturn == true) {
                cells[1] = 'X'
                checkResult(cells)
                PrintBoard(cells)
                CoorFun(cells, xturn = false)
            } else {
                cells[1] = 'O'
                checkResult(cells)
                PrintBoard(cells)
                CoorFun(cells, xturn = true)
            }

        }
        "13" -> if (cells[2] != ' ') {
            println("This cell is occupied! Choose another one!")
            CoorFun(cells, xturn)
        } else {
            if (xturn == true) {
                cells[2] = 'X'
                checkResult(cells)
                PrintBoard(cells)
                CoorFun(cells, xturn = false)
            } else {
                cells[2] = 'O'
                checkResult(cells)
                PrintBoard(cells)
                CoorFun(cells, xturn = true)
            }
        }
        "21" -> if (cells[3] != ' ') {
            println("This cell is occupied! Choose another one!")
            CoorFun(cells, xturn)
        } else {
            if (xturn == true) {
                cells[3] = 'X'
                checkResult(cells)
                PrintBoard(cells)
                CoorFun(cells, xturn = false)
            } else {
                cells[3] = 'O'
                checkResult(cells)
                PrintBoard(cells)
                CoorFun(cells, xturn = true)
            }

        }
        "22" -> if (cells[4] != ' ') {
            println("This cell is occupied! Choose another one!")
            CoorFun(cells, xturn)
        } else {
            if (xturn == true) {
                cells[4] = 'X'
                checkResult(cells)
                PrintBoard(cells)
                CoorFun(cells, xturn = false)
            } else {
                cells[4] = 'O'
                checkResult(cells)
                PrintBoard(cells)
                CoorFun(cells, xturn = true)
            }
        }
        "23" -> if (cells[5] != ' ') {
            println("This cell is occupied! Choose another one!")
            CoorFun(cells, xturn)
        } else {
            if (xturn == true) {
                cells[5] = 'X'
                checkResult(cells)
                PrintBoard(cells)
                CoorFun(cells, xturn = false)
            } else {
                cells[5] = 'O'
                checkResult(cells)
                PrintBoard(cells)
                CoorFun(cells, xturn = true)
            }
        }
        "31" -> if (cells[6] != ' ') {
            println("This cell is occupied! Choose another one!")
            CoorFun(cells, xturn)
        } else {
            if (xturn == true) {
                cells[6] = 'X'
                checkResult(cells)
                PrintBoard(cells)
                CoorFun(cells, xturn = false)
            } else {
                cells[6] = 'O'
                checkResult(cells)
                PrintBoard(cells)
                CoorFun(cells, xturn = true)
            }
        }
        "32" -> if (cells[7] != ' ') {
            println("This cell is occupied! Choose another one!")
            CoorFun(cells, xturn)
        } else {
            if (xturn == true) {
                cells[7] = 'X'
                checkResult(cells)
                PrintBoard(cells)
                CoorFun(cells, xturn = false)
            } else {
                cells[7] = 'O'
                checkResult(cells)
                PrintBoard(cells)
                CoorFun(cells, xturn = true)
            }
        }
        "33" -> if (cells[8] != ' ') {
            println("This cell is occupied! Choose another one!")
            CoorFun(cells, xturn)
        } else {
            if (xturn == true) {
                cells[8] = 'X'
                checkResult(cells)
                PrintBoard(cells)
                CoorFun(cells, xturn = false)
            } else {
                cells[8] = 'O'
                checkResult(cells)
                PrintBoard(cells)
                CoorFun(cells, xturn = true)
            }
        }
    }

}