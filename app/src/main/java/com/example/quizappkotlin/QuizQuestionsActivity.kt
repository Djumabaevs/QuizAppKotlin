package com.example.quizappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizappkotlin.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity() {
    lateinit var binding2: ActivityQuizQuestionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
    }
}