package com.example.quizappkotlin

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quizappkotlin.databinding.ActivityQuizQuestionsBinding
import java.security.SecureRandomSpi

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding2: ActivityQuizQuestionsBinding
    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var  mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding2 = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding2.root)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList = Constants.getQuestions()

        setQuestion()

        binding2.tvOptionOne.setOnClickListener(this)
        binding2.tvOptionTwo.setOnClickListener(this)
        binding2.tvOptionThree.setOnClickListener(this)
        binding2.tvOptionFour.setOnClickListener(this)
        binding2.submitBtn.setOnClickListener(this)

    }

    private fun setQuestion() {

        val question = mQuestionsList!![mCurrentPosition - 1]

        defaultOptionsView()

        if(mCurrentPosition == mQuestionsList!!.size) {
            binding2.submitBtn.text = getString(R.string.finish_tn)
        } else {
            binding2.submitBtn.text = getString(R.string.submit_tn)
        }

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
            R.id.submit_btn -> {
                if(mSelectedOptionPosition == 0) {
                    mCurrentPosition ++

                    when{
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        } else -> {
                        val intent = Intent(this, ResultActivity:: class.java)
                        intent.putExtra(Constants.USER_NAME, mUserName)
                        intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                        intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)

                        Toast.makeText(this, "You have successfully completed the Quiz!", Toast.LENGTH_SHORT).show()
                       }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if(question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionsList?.size) {
                        binding2.submitBtn.text = getString(R.string.finish)
                    } else {
                        binding2.submitBtn.text = getString(R.string.go_next_question)
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when(answer) {
            1 -> { binding2.tvOptionOne.background = ContextCompat.getDrawable(this, drawableView) }
            2 -> { binding2.tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView) }
            3 -> { binding2.tvOptionThree.background = ContextCompat.getDrawable(this, drawableView) }
            4 -> { binding2.tvOptionFour.background = ContextCompat.getDrawable(this, drawableView) }
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