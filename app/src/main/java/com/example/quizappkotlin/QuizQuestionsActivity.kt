package com.example.quizappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.quizappkotlin.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity() {
    lateinit var binding2: ActivityQuizQuestionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding2 = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding2.root)

        val questionsList = Constants.getQuestions()
        Log.i("Questions"," ${questionsList.size}")

    }
}