package com.example.quizapp

import android.content.Intent
import android.graphics.BitmapFactory.Options
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.quizapp.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var myCurrentPosition: Int = 1
    private var myQuestionsList: ArrayList<Question>? = null
    private var mySelectedOptionPosition: Int = 0
    private var myUserName: String? = null
    private var myCorrectAnswers: Int = 0

    lateinit var binding: ActivityQuizQuestionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myQuestionsList = Constance.getQuestions()

        myUserName = intent.getStringExtra(Constance.USER_NAME)

        setQuestion()
        //defaultOptionsView()


    }

    private fun setQuestion() {

        defaultOptionsView()
        val mainQuestion: Question = myQuestionsList!![myCurrentPosition - 1]
        val progressBar = binding.progressBar
        var progressText = binding.textProgress
        var question = binding.textQuestion
        var image = binding.imageOfPerson
        var optionOne = binding.textOption1
        var optionTwo = binding.textOption2
        var optionThree = binding.textOption3
        var optionFour = binding.textOption4

        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)

        binding.buttonSubmit.setOnClickListener(this)


        image.setImageResource(mainQuestion.image)

        progressBar?.progress = myCurrentPosition
        progressText.text = "$myCurrentPosition/${progressBar.max}"
        question.text = mainQuestion.question

        optionOne.text = mainQuestion.option1
        optionTwo.text = mainQuestion.option2
        optionThree.text = mainQuestion.option3
        optionFour.text = mainQuestion.option4

        if (myCurrentPosition == myQuestionsList!!.size) {
            binding.buttonSubmit.text = "Узнать результаты"
        } else {
            binding.buttonSubmit.text = "Принять ответ"
        }

    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        binding.textOption1?.let {
            options.add(0, it)
        }
        binding.textOption2?.let {
            options.add(1, it)
        }
        binding.textOption3?.let {
            options.add(2, it)
        }
        binding.textOption4?.let {
            options.add(3, it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mySelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.textOption1 -> {
                binding.textOption1?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.textOption2 -> {
                binding.textOption2?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.textOption3 -> {
                binding.textOption3?.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.textOption4 -> {
                binding.textOption4?.let {
                    selectedOptionView(it, 4)
                }
            }

            R.id.buttonSubmit -> {
                if (mySelectedOptionPosition == 0) {
                    myCurrentPosition++

                    when {
                        myCurrentPosition <= myQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constance.USER_NAME, myUserName)
                            intent.putExtra(Constance.CORRECT_ANSWERS, myCorrectAnswers)
                            intent.putExtra(Constance.TOTAL_QUESTIONS, myQuestionsList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = myQuestionsList?.get(myCurrentPosition - 1)
                    if (question!!.correctAnswer != mySelectedOptionPosition) {
                        answerView(mySelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        myCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (myCurrentPosition == myQuestionsList!!.size) {
                        binding.buttonSubmit.text = "Узнать результаты"
                        binding.buttonSubmit.setBackgroundColor(ContextCompat.getColor(this, R.color.Back))
                    } else {
                        binding.buttonSubmit.text = "Следующий вопрос"
                    }

                    mySelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                binding.textOption1.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                binding.textOption2.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                binding.textOption3.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                binding.textOption4.background = ContextCompat.getDrawable(this, drawableView)
            }
        }

    }
}