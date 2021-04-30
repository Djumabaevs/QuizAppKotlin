package com.example.quizappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.quizappkotlin.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity() {
    lateinit var binding2: ActivityQuizQuestionsBinding
    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding2 = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding2.root)

        mQuestionsList = Constants.getQuestions()

        setQuestion()

    }

    private fun setQuestion() {
        mCurrentPosition = 1
        val question = mQuestionsList!![mCurrentPosition - 1]

        binding2.progressBar.progress = mCurrentPosition
        binding2.tvProgress.text = "$mCurrentPosition" + "/" + binding2.progressBar.max
        binding2.tvQuestion.text = question!!.question
        binding2.ivImage.setImageResource(question.image)
        binding2.tvOptionOne.text = question.optionOne
        binding2.tvOptionTwo.text = question.optionTwo
        binding2.tvOptionThree.text = question.optionThree
        binding2.tvOptionFour.text = question.optionFour
    }
}