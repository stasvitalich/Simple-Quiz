package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast

import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var cardView = binding.cardView
        val animation = AnimationUtils.loadAnimation(this, R.anim.cardanimation)
        cardView.startAnimation(animation)


        var yourName = binding.textEditName
        binding.buttonStart.setOnClickListener {
            if (yourName.text?.isEmpty() == true){
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show()
            } else{
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constance.USER_NAME, yourName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}