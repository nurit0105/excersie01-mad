/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package at.ac.fhcampuswien

class App {
    // Game logic for a number guessing game
    //TODO: build a menu which calls the functions and works with the return values
    fun playNumberGame(digitsToGuess: Int = 4) {
        val generatedNumber = generateRandomNonRepeatingNumber(digitsToGuess)

        println("Welcome to the Number Guessing Game! Try to guess the $digitsToGuess-digit number.")

        do {
            println("Please enter a $digitsToGuess-digit number:")
            val userInput = readLine()?.toIntOrNull()

            if (userInput != null) {
                val compareResult = checkUserInputAgainstGeneratedNumber(userInput, generatedNumber)
                println("Result: ${compareResult.m} correct digits at the correct position, ${compareResult.n} correct digits in general.")

                if (compareResult.m == digitsToGuess) {
                    println("Congratulations! Your guess is correct.")
                    return
                }
            } else {
                println("Invalid input. Please enter a valid number.")
            }
        } while (true)
}


/**
 * Generates a non-repeating number of a specified length between 1-9.
 *
 * Note: The function is designed to generate a number where each digit is unique and does not repeat.
 * It is important to ensure that the length parameter does not exceed the maximum possible length
 * for non-repeating digits (which is 9 excluding 0 for base-10 numbers).
 *
 * @param length The length of the non-repeating number to be generated.
 *               This dictates how many digits the generated number will have.
 * @return An integer of generated non-repeating number.
 *         The generated number will have a number of digits equal to the specified length and will
 *         contain unique, non-repeating digits.
 * @throws IllegalArgumentException if the length is more than 9 or less than 1.
 */
//TODO implement the function
val generateRandomNonRepeatingNumber: (Int) -> Int = { length ->
    when {
        length < 1 -> throw IllegalArgumentException("Length must be >= 1")
        length > 9 -> throw IllegalArgumentException("Length must be <= 9.")
        else -> (1..9).shuffled().take(length).joinToString("").toInt()
    }

}

/**
 * Compares the user's input integer against a generated number for a guessing game.
 * This function evaluates how many digits the user guessed correctly and how many of those
 * are in the correct position. The game generates number with non-repeating digits.
 *
 * Note: The input and the generated number must both be numbers.
 * If the inputs do not meet these criteria, an IllegalArgumentException is thrown.
 *
 * @param input The user's input integer. It should be a number with non-repeating digits.
 * @param generatedNumber The generated number with non-repeating digits to compare against.
 * @return [CompareResult] with two properties:
 *         1. `n`: The number of digits guessed correctly (regardless of their position).
 *         2. `m`: The number of digits guessed correctly and in the correct position.
 *         The result is formatted as "Output: m:n", where "m" and "n" represent the above values, respectively.
 * @throws IllegalArgumentException if the inputs do not have the same number of digits.
 */

//TODO implement the function
val checkUserInputAgainstGeneratedNumber: (Int, Int) -> CompareResult = { input, generatedNumber ->
    val inputString = input.toString()
    val generatedString = generatedNumber.toString()

    require(inputString.length == generatedString.length) { "Not the same number of digits" }

    val m = inputString.zip(generatedString).count { (userDigit, generatedDigit) -> // zip --> "pairing" numbers to each other
        userDigit == generatedDigit
    }

    val n = inputString.toSet().count { correctDigit -> // toSet() --> removing doubles
        generatedString.contains(correctDigit)
    }

    CompareResult(n, m)
}


}
fun main() {
    // TODO: call the App.playNumberGame function with and without default arguments
    println("Welcome to the Number Guessing Game!")
    val app = App()

    println("Do you want to play with the default number of digits (enter 1) or a random number between 1 and 9 (enter 2)?:")

    when (readlnOrNull()?.toIntOrNull()) {
        1 -> app.playNumberGame()
        2 -> {
            println("Enter the number of digits for the random number:")
            val digitsToGuess = readlnOrNull()?.toIntOrNull()

            if (digitsToGuess != null && digitsToGuess > 0 && digitsToGuess <= 9) {
                app.playNumberGame(digitsToGuess)
            } else {
                println("Invalid input for the number of digits. Using the default value (4).")
                app.playNumberGame()
            }
        }
        else -> println("Invalid input. Exiting the game.")
    }
}


