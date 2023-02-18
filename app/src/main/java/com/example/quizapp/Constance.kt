package com.example.quizapp

object Constance {

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val question1 = Question(
            1, "Кто изображён на фотографии?",
            R.drawable.aleksanderperv,
            "Александр 3",
            "Николай 1",
            "Александр 1",
            "Пётр 3",
            1
        )

        questionsList.add(question1)
        return questionsList
    }
}