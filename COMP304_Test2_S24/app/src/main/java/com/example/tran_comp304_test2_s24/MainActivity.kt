package com.example.tran_comp304_test2_s24

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private var score = 0
    private var currentQuestionIndex = 0
    private val questions = listOf(
        "Select your First Name" to listOf("Thanh", "Tran", "Thanh Dat Tran"),
        "Select your Last Name" to listOf("Thanh", "Tran", "Thanh Dat Tran"),
        "Select your Student Number" to listOf("301369685", "301369684", "301369686")
    )
    private val correctAnswers = listOf(0, 1, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadQuestion()
    }

    private fun loadQuestion() {
        if (currentQuestionIndex < questions.size) {
            resetButtonColors() // Reset button colors to default before loading the next question
            val (question, answers) = questions[currentQuestionIndex]

            findViewById<TextView>(R.id.question_text).text = question
            findViewById<Button>(R.id.answer_button_1).text = answers[0]
            findViewById<Button>(R.id.answer_button_2).text = answers[1]
            findViewById<Button>(R.id.answer_button_3).text = answers[2]

            setupButtonListeners(answers)
        } else {
            showFinalScore()
        }
    }

    private fun resetButtonColors() {
        val defaultColor = ContextCompat.getColor(this, android.R.color.darker_gray) // Set your default button color here

        findViewById<Button>(R.id.answer_button_1).setBackgroundColor(defaultColor)
        findViewById<Button>(R.id.answer_button_2).setBackgroundColor(defaultColor)
        findViewById<Button>(R.id.answer_button_3).setBackgroundColor(defaultColor)
    }


    private fun setupButtonListeners(answers: List<String>) {
        val buttons = listOf(
            findViewById<Button>(R.id.answer_button_1),
            findViewById<Button>(R.id.answer_button_2),
            findViewById<Button>(R.id.answer_button_3)
        )

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                handleAnswerSelection(index == correctAnswers[currentQuestionIndex], button)
            }
        }
    }

    private fun handleAnswerSelection(isCorrect: Boolean, button: Button) {
        if (isCorrect) {
            score++
            button.setBackgroundColor(Color.GREEN)
            Toast.makeText(this, "Hi<LastName> your score is $score out of 3", Toast.LENGTH_SHORT).show()
        } else {
            button.setBackgroundColor(Color.RED)
            Toast.makeText(this, "Hi<LastName> your score is $score out of 3", Toast.LENGTH_SHORT).show()
        }

        // Add a delay before moving to the next question
        Handler(Looper.getMainLooper()).postDelayed({
            currentQuestionIndex++
            loadQuestion()
        }, 2000) // 2000 milliseconds delay (2 seconds)
    }


    private fun showFinalScore() {
        Toast.makeText(this, "Quiz Complete! Your final score is $score out of 3", Toast.LENGTH_LONG).show()
    }
}
