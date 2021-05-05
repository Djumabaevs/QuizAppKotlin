package com.example.quizappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizappkotlin.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var bindingResult: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        bindingResult = ActivityResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bindingResult.root)

        val username = intent.getStringExtra(Constants.USER_NAME)
        bindingResult.tvName.text = username

    }
}