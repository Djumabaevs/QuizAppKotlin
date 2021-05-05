package com.example.quizappkotlin

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.quizappkotlin.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var bindingResult: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        bindingResult = ActivityResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bindingResult.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        val username = intent.getStringExtra(Constants.USER_NAME)
        bindingResult.tvName.text = username

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        bindingResult.tvScore.text = "Your score is $correctAnswers out of $totalQuestions"

        bindingResult.btnFinish.setOnClickListener {
            startActivity(Intent(this, MainActivity:: class.java))
            finish()
        }

    }
}