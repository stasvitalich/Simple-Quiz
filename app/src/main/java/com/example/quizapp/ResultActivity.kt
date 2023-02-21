package com.example.quizapp


import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils

import com.example.quizapp.databinding.ActivityResultBinding
import kotlinx.coroutines.delay
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var cardView = binding.imageView2
        val animation = AnimationUtils.loadAnimation(this, R.anim.cardanimation)
        cardView.startAnimation(animation)

        binding.textName.text = intent.getStringExtra(Constance.USER_NAME)
        val totalQuestions = intent.getIntExtra(Constance.TOTAL_QUESTIONS, 0)
        val correctAnswer = intent.getIntExtra(Constance.CORRECT_ANSWERS, 0)

        binding.textFinalResult.text =
            "Вы дали ${correctAnswer.toString()} верных ответов из ${totalQuestions.toString()}"

        var mpbuttonsound = MediaPlayer()

        if (correctAnswer in 0..10) {
            binding.textRecommendation.text = "Вы тупой как камень"
            mpbuttonsound.setDataSource(
                this,
                Uri.parse("android.resource://" + this.packageName + "/" + R.raw.under10)
            )
            mpbuttonsound.prepare()
            mpbuttonsound.start()
        }
        if (correctAnswer in 11..19) {
            binding.textRecommendation.text = "Вы что-то слышали о политике..."
            mpbuttonsound.setDataSource(
                this,
                Uri.parse("android.resource://" + this.packageName + "/" + R.raw.under20)
            )
            mpbuttonsound.prepare()
            mpbuttonsound.start()
        }
        if (correctAnswer in 20..29) {
            binding.textRecommendation.text = "Довольно неплохой результат"
            mpbuttonsound.setDataSource(
                this,
                Uri.parse("android.resource://" + this.packageName + "/" + R.raw.under30)
            )
            mpbuttonsound.prepare()
            mpbuttonsound.start()
        }
        if (correctAnswer in 30..39) {
            binding.textRecommendation.text = "Хороший результат"
            mpbuttonsound.setDataSource(
                this,
                Uri.parse("android.resource://" + this.packageName + "/" + R.raw.under40)
            )
            mpbuttonsound.prepare()
            mpbuttonsound.start()
        }
        if (correctAnswer in 40..59) {
            binding.textRecommendation.text = "Отличный результат!"
            mpbuttonsound.setDataSource(
                this,
                Uri.parse("android.resource://" + this.packageName + "/" + R.raw.under50)
            )
            mpbuttonsound.prepare()
            mpbuttonsound.start()
        }
        if (correctAnswer == 60) {
            binding.textRecommendation.text = "Невероятный результат!"
            mpbuttonsound.setDataSource(
                this,
                Uri.parse("android.resource://" + this.packageName + "/" + R.raw.exact60)
            )
            mpbuttonsound.prepare()
            mpbuttonsound.start()
        }



        initUI()

        binding.button.setOnClickListener {
            //finishAndRemoveTask()
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    private fun initUI() {
        val party = Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            delay = 900,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )

        binding.konfettiView.start(party)

    }
}