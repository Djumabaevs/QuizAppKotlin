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

        val currentPosition = 1
        val question: Question? = questionsList[currentPosition - 1]

        binding2.progressBar.progress = currentPosition
        binding2.tvProgress.text = "$currentPosition" + "/" + binding2.progressBar.max
        binding2.tvQuestion.text = question!!.question
        binding2.ivImage.setImageResource(question.image)
        binding2.tvOptionOne.text = question.optionOne
        binding2.tvOptionTwo.text = question.optionTwo
        binding2.tvOptionThree.text = question.optionThree
        binding2.tvOptionFour.text = question.optionFour


    }
}