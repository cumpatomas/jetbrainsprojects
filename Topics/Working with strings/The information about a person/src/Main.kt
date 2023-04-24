import java.lang.Exception

fun main() {
    val info = readLine()!!.split(" ")
    try {
        print("${info[0].first()}. ${info[1]}, ${info[2]} years old")
    } catch (e: Exception) {
        print("Wrong format input")
    }
}