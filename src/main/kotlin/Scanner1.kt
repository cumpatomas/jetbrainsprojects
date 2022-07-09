/*Example of getting only Int from the user's input
* avoiding whitespaces using Scanner.
* Also it finds the bigger number and the times it appears
*/
import java.util.Scanner

fun main() {

    var bigger = 0
    var times = 1
    val list = mutableListOf<Int>()
    val scanner = Scanner(System.`in`)

    while (scanner.hasNext()) {
        list.add(scanner.nextInt())
        if (list[list.lastIndex] > bigger) {
            bigger = list[list.lastIndex]
            times = 1
        } else if (list[list.lastIndex] == bigger) {
            times++
        }

    }
    println("Total numbers: ${list.size}.")
    println("The greatest number: $bigger ($times time(s)).")


}