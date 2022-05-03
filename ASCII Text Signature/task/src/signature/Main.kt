package signature

import java.io.File

fun main() {

    println("Enter name and surname: ")
    val name = readln()!!
    println("Enter person's status: ")
    val status = readln()!!

    val nameSplit = name.split("").toMutableList()
    nameSplit.removeAt(0)
    nameSplit.removeLast()
    val statusSplit = status.split("").toMutableList()
    statusSplit.removeAt(0)
    statusSplit.removeLast()
    var ancho = 10 // for the whitespace in between
    var ancho2 = 0
    for (i in statusSplit) { // for the whitespaces in between words

        if (i.isBlank()) {
            ancho2 += 5
        }
    }
    /*  if (" " in statusSplit) { ancho2 = 5 } */

    for (i in nameSplit) {

        ancho += getSize(i)

    }
    for (i in statusSplit) {

        ancho2 += getStatusSize(i)

    }

    if (ancho > ancho2) { //--------------print Name larger than status
        //------print top bar
        print("88888888") // for the first column and the 2 whitespaces
        var barAncho1 = 8
        //----print rest of top bar depending on Name length
        repeat(ancho) {
            print("8")
            barAncho1++
        } //----------print Name when Larger than Status

        println()

        print("88  ")
        for (i in 1..10) {

            for (j in nameSplit) {

                print(getName(j, i))

            }
            print("  88")
            println()
            if (i != 10) print("88  ") else print("88")
        }
        //----------print Status when shorter than Name

        for (i in 1..3) {
            if (i != 1) {
                print("88")
            }

            repeat(((barAncho1 - 4) - ancho2) / 2) { print(" ") } // we substract 2 for the 2 whitespaces


            for (j in statusSplit) {

                print(getStatus(j, i))

            }

            if (barAncho1 % 2 == 0 && ancho2 % 2 == 0 || barAncho1 % 2 != 0 && ancho2 % 2 != 0) {
                repeat((((barAncho1 - 4) - ancho2) / 2)) { print(" ") }
            } else {
                repeat((((barAncho1 - 4) - ancho2) / 2) + 1) { print(" ") }
            }

            print("88")
            println()
        }
        print("88888888") // for the first column and the 2 whitespaces
        repeat(ancho) { print("8") }

        //---------STATUS larger than name
    } else {


        print("88888888") // for the first column and the 2 whitespaces
        var barAncho = 8
        repeat(ancho2) {
            print("8")
            barAncho++
        }
        println()

//-----------------print the name when shorter than STATUS
        for (i in 1..10) {
            print("88")

            repeat(((barAncho - ancho) / 2) - 2) { print(" ") }

            for (j in nameSplit) {

                print(getName(j, i))

            }
            /*repeat(whiteSpaces + 2) { print(" ") }*/
            repeat((((barAncho - 2) - ancho) - (barAncho - ancho) / 2)) { print(" ") }
            print("88")
            println()
        }
        //-----------print STATUS larger than name
        for (i in 1..3) {
            print("88  ")
            for (j in statusSplit) {

                print(getStatus(j, i))

            }
            print("  88")
            println()
        }
        print("88888888") // for the first column and the 2 whitespaces
        repeat(ancho2) { print("8") }
    }

}

fun getName(letter: String, line: Int): String {

    val file = """C:\Users\cumpa\IdeaProjects\ASCII Text Signature\ASCII Text Signature\task\src\roman.txt"""
    val lines = File(file).readLines().toMutableList()

    val regex = Regex("""$letter \d\d?""")
    val regex2 = Regex("""\d\d \d\d""")
    var output = ""

    label@ for (i in lines) {

        if (regex2.matches(i)) {
            continue
        }
        if (regex.matches(i)) {
            output = lines[lines.indexOf(i) + line]

        }
        if (letter.isBlank()) {
            output = "          "
        }

    }
    return output

}

fun getStatus(letter: String, line: Int): String {

    val file = """C:\Users\cumpa\IdeaProjects\ASCII Text Signature\ASCII Text Signature\task\src\medium.txt"""
    val lines = File(file).readLines().toMutableList()

    val regex = Regex("""$letter \d\d?""")
    val regex2 = Regex("""\d\d \d\d""")
    var output = ""

    label@ for (i in lines) {

        if (regex2.matches(i)) {
            continue
        }
        if (regex.matches(i)) {
            output = lines[lines.indexOf(i) + line]

        }

        if (letter.isBlank()) {
            output = "     "

        }

    }
    return output

}

fun getSize(letter: String): Int {

    // name SIZE
    val file = """C:\Users\cumpa\IdeaProjects\ASCII Text Signature\ASCII Text Signature\task\src\roman.txt"""
    val lines = File(file).readLines().toMutableList()
    val regex = Regex("""$letter \d\d?""")
    var ancho = 0
    label@ for (i in lines) {

        if (regex.matches(i)) {
            ancho = ancho + i.drop(2).toInt()
            break@label
        }
    }

    return ancho
}

fun getStatusSize(letter: String): Int {

    val file2 = """C:\Users\cumpa\IdeaProjects\ASCII Text Signature\ASCII Text Signature\task\src\medium.txt"""
    val lines2 = File(file2).readLines().toMutableList()
    val regex2 = Regex("""$letter \d\d?""")
    var ancho2 = 0

    label@ for (i in lines2) {

        if (regex2.matches(i)) {
            ancho2 = ancho2 + i.drop(2).toInt()
        }

    }
    return ancho2
}