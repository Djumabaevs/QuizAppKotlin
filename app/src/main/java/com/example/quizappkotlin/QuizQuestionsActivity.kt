package com.example.quizappkotlin

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.quizappkotlin.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
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

        binding2.tvOptionOne.setOnClickListener(this)
        binding2.tvOptionTwo.setOnClickListener(this)
        binding2.tvOptionThree.setOnClickListener(this)
        binding2.tvOptionFour.setOnClickListener(this)

    }

    private fun setQuestion() {
        mCurrentPosition = 1
        val question = mQuestionsList!![mCurrentPosition - 1]

        defaultOptionsView()

        binding2.progressBar.progress = mCurrentPosition
        binding2.tvProgress.text = "$mCurrentPosition" + "/" + binding2.progressBar.max
        binding2.tvQuestion.text = question!!.question
        binding2.ivImage.setImageResource(question.image)
        binding2.tvOptionOne.text = question.optionOne
        binding2.tvOptionTwo.text = question.optionTwo
        binding2.tvOptionThree.text = question.optionThree
        binding2.tvOptionFour.text = question.optionFour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, binding2.tvOptionOne)
        options.add(1, binding2.tvOptionTwo)
        options.add(2, binding2.tvOptionThree)
        options.add(3, binding2.tvOptionFour)

        for(option in options) {
            option.setTextColor(Color.parseColor("#7a8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(binding2.tvOptionOne, 1)
            }
            R.id.tv_option_two -> {
              selectedOptionView(  binding2.tvOptionTwo, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(binding2.tvOptionThree, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(binding2.tvOptionFour, 4)
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNumber

        tv.setTextColor(Color.parseColor("#363a43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }
}