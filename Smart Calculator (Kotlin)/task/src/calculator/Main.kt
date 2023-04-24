package calculator

import java.math.BigInteger
import kotlin.math.pow
import kotlin.system.exitProcess

val assignmentList = mutableMapOf<String, String>()


fun main() {

    calculate()
}

private fun calculate() {
    val namesRegex = "[a-z, A-Z, \\d, +, -]".toRegex()
    val input = readln().replace(" ", "")

    if (input.isEmpty()) calculate()

    if (input.first() == '/') {
        when (input) {
            "/exit" -> {
                println("Bye!")
                exitProcess(0)
            }

            "/help" -> {
                println("The program calculates the sum or subtractions entered")
                calculate()
            }

            else -> {
                println("Unknown command")
                calculate()
            }
        }
    }


    if (!namesRegex.containsMatchIn(input)
        || (input.contains('(') && !input.contains(')')
        || input.contains(')') && !input.contains('('))
        || input.contains("\\*{2,}".toRegex())
        || input.contains("\\/{2,}".toRegex())

        ) {
        println("Invalid expression")
        calculate()
    } else {
        if(input.contains("(") && input.contains(")") ) {
            val postfix = infixToPostfix(input)
//            println(postfix)
            println(resolvePostFix(postfix))
            calculate()

        }

        if (input.isEmpty()) {
            calculate()
        } else {
            if (!input.contains("=")) {
                println(getExpressions(input))
                calculate()
            } else checkAssignment(input.split("").filter { it.isNotEmpty() }.toMutableList())


        }
    }
}

fun getExpressions(input: String): BigInteger {

    if (input.matches("^-?\\d+\$".toRegex())) return input.toBigInteger()


    val numbersOrVars = input.split("[-,[+],[*],[/]]".toRegex()).toMutableList().filter { it.isNotEmpty() }.toMutableList()
    val numbersOrVarsNewList = mutableListOf<String>()
    val pattern = Regex("[+]+|[-]+|[*]|[/]")
    val matches = pattern.findAll(input)
    val operations = matches.map { it.value }.toMutableList()

numbersOrVars.forEach {
    if (!it.matches("^-?\\d+\$".toRegex())) numbersOrVars[numbersOrVars.indexOf(it)] = assignmentList[it].toString()
}
/*    println(input)
    println(operations)
    println(numbersOrVars)*/

    if (!numbersOrVars.first().matches("^-?\\d+\$".toRegex()) && numbersOrVars.first() !in assignmentList.keys) {
        println("Unknown variable")
        calculate()
    }

    var result: BigInteger = if (!numbersOrVars.first().matches("^-?\\d+\$".toRegex()) && numbersOrVars.first() in assignmentList.keys) {

        assignmentList[numbersOrVars.first()]!!.toBigInteger()

    } else {
        numbersOrVars.first().toBigInteger()
        }

    if (operations.isEmpty() && !input.contains("\\w".toRegex())) result = input.toBigInteger()

    for (i in numbersOrVars) {
        if (!i.matches("^-?\\d+\$".toRegex()) && i !in assignmentList.keys) {
            println("Unknown variable")
            calculate()
        }

        if (i in assignmentList.keys) {
            numbersOrVarsNewList.add(assignmentList[i].toString())

        } else {
            numbersOrVarsNewList.add(i)
        }
    }

    for (sign in operations.indices) {

        if (operations[sign].matches("\\+{2,}".toRegex())) {
            result += numbersOrVarsNewList[sign + 1].toBigInteger()
            continue
        }
        if(operations[sign].matches("-{3,}".toRegex())) {
            if (operations[sign].length % 2 == 0 ) result += numbersOrVarsNewList[sign + 1].toBigInteger()
            if (operations[sign].length % 2 != 0 ) result -= numbersOrVarsNewList[sign + 1].toBigInteger()

            continue
        }
        else if(operations[sign] ==  "+" ) {
            result += numbersOrVarsNewList[sign + 1].toBigInteger()
            continue
        }
        else if(operations[sign] ==  "--" ) {
            result += numbersOrVarsNewList[sign + 1].toBigInteger()
            continue
        }
        else if(operations[sign] ==  "-" ) {
            result -= numbersOrVarsNewList[sign + 1].toBigInteger()
            continue
        }
        else if(operations[sign] ==  "*" ) {
            result *= numbersOrVarsNewList[sign + 1].toBigInteger()
            continue
        }
        else if(operations[sign] ==  "/" ) {
            result /= numbersOrVarsNewList[sign + 1].toBigInteger()

            continue
        } else {
            println("Invalid expression")
            calculate()
        }

    }
    return result
}

fun checkAssignment(list: MutableList<String>) {
    val regexAssign = "[a-zA-Z]+=[0-9]*[a-zA-Z]*".toRegex()
    val regexInvalidId = "\\w*\\d\\w*".toRegex() // match adjacent digit and word
    val regexInvalidId2 = "\\d\\w*|\\w+\\d".toRegex() // match digit and word at start or end

    val assignString = list.joinToString("").filter { it.toString().isNotEmpty() }

    if (!assignString.matches(regexAssign)) {
        println("Invalid assignment")
        calculate()
        return
    }

    val identifier = assignString.substringBefore("=")
    val value = assignString.substringAfter("=")

    if (value.matches("-?\\d+".toRegex())) {
        assignmentList[identifier] = value
        calculate()
    } else if (value.matches(regexInvalidId) || value.matches(regexInvalidId2)) {
        println("Invalid identifier")
        calculate()
    } else if (value !in assignmentList) {
        println("Unknown variable")
        calculate()
    } else {
        assignmentList[identifier] = assignmentList[value]!!
        calculate()
    }
}


fun infixToPostfix(infix: String): String {
    val outputQueue = mutableListOf<String>()
    val operatorStack = mutableListOf<Char>()
    var tempNumber = ""

    infix.forEach { char ->
        when {
            char.isLetterOrDigit() -> {
                tempNumber += char
            }
            char in "+-*/^" -> {
                if (tempNumber.isNotEmpty()) {
                    outputQueue.add(tempNumber)
                    tempNumber = ""
                }
                while (operatorStack.isNotEmpty() && precedence(operatorStack.last()) >= precedence(char)) {
                    outputQueue.add(operatorStack.removeLast().toString())
                }
                operatorStack.add(char)
            }
            char == '(' -> operatorStack.add(char)
            char == ')' -> {
                if (tempNumber.isNotEmpty()) {
                    outputQueue.add(tempNumber)
                    tempNumber = ""
                }
                while (operatorStack.isNotEmpty() && operatorStack.last() != '(') {
                    outputQueue.add(operatorStack.removeLast().toString())
                }
                operatorStack.removeLast()
            }
        }
    }

    if (tempNumber.isNotEmpty()) {
        outputQueue.add(tempNumber)
    }

    while (operatorStack.isNotEmpty()) {
        outputQueue.add(operatorStack.removeLast().toString())
    }

    return outputQueue.joinToString(" ")
}




private fun precedence(operator: Char): Int {
    return when (operator) {
        '+', '-' -> 1
        '*', '/' -> 2
        '^' -> 3
        '(', ')' -> 0
        else -> throw IllegalArgumentException("Unsupported operator $operator")
    }
}

private fun resolvePostFix(input: String): String {
    var _input = input

    assignmentList.forEach { (key, value) ->
        _input = _input.replace(key, value)
    }

    val stack = mutableListOf<BigInteger>()

    _input.split(" ").forEach { token ->

        when {
            token.matches(Regex("-?\\d+")) -> stack.add(token.toBigInteger())
            token in "+-*/^" -> {
                if (stack.size < 2) {
                    throw IllegalArgumentException("Invalid expression: too many operators")
                }
                val operand2 = stack.removeLast()
                val operand1 = stack.removeLast()
                val result = when (token) {
                    "+" -> operand1 + operand2
                    "-" -> operand1 - operand2
                    "*" -> operand1 * operand2
                    "/" -> operand1 / operand2
                    "^" -> operand1.toDouble().pow(operand2.toDouble()).toInt().toBigInteger()
                    else -> throw IllegalArgumentException("Unsupported operator $token")
                }
                stack.add(result)
            }
        }
    }

    if (stack.size > 1) {
        throw IllegalArgumentException("Invalid expression: too many operands")
    }

    return stack.single().toString()
}

