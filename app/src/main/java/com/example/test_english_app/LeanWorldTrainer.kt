package com.example.test_english_app


data class Word(
    val original: String,
    val translate: String,
    var learned: Boolean = false,
)

data class Question(
    val variants: List<Word>,
    val correctAnswer: Word,
)

class LearnWordsTrainer {

    private val dictionary = listOf(
        Word("Vogon", "Ворон"),
        Word("Babel fish", "Бабель-рыба"),
        Word("Gargle Blaster", "Громоглот"),
        Word("Hyperdrive", "Гипердвигатель"),
        Word("i Love You", "Я тебя люблю"),
        Word("Hyperdrive", "Гипердвигатель"),
        Word("Hooloovoo", "Хулвуу"),
        Word("Magrathea", "Маргатея"),
        Word("Infinite Improbability", "Бесконечная Вероятность"),
        Word("Hyper Space", "Гиперпространство"),
        Word("Guidebook", "Путеводитель"),
        Word("Starship", "Звездолет"),
        Word("Towel", "Полотенце"),
        Word("Paranoid Android", "Параноидальный Андроид"),
        Word("Pan Galactic", "Пангалактический"),
        Word("Deep Thought", "Глубокая Мысль"),
        Word("Teleport", "Телепорт"),
        Word("Mind", "Разум"),
        Word("Universe", "Вселенная"),
        Word("Hitchhiker", "Автостопщик"),
        Word("Whale", "Кит"),
        Word("Petunias", "Петунии"),
        Word("Heart of Gold", "Сердце Золота"),
        Word("Galaxy", "Галактика"),
        Word("End of the Universe", "Конец Вселенной"),
        Word("Space", "Космос"),
        Word("Probability", "Вероятность"),
        Word("Probability", "Вероятность")

    )

    private var currentQuestion: Question? = null

    fun getNextQuestion(): Question? {

        val notLearnedList = dictionary.filter { !it.learned }
        if (notLearnedList.isEmpty()) return null

        val questionWords =
            if (notLearnedList.size < NUMBER_OF_ANSWERS) {
            val learnedList = dictionary.filter { it.learned }.shuffled()
            (notLearnedList.shuffled() + learnedList.take(NUMBER_OF_ANSWERS - notLearnedList.size)).shuffled()
        } else {
            notLearnedList.shuffled().take(NUMBER_OF_ANSWERS)
        }.shuffled()

        val correctAnswer = questionWords.random()

        currentQuestion = Question(
            variants = questionWords,
            correctAnswer = correctAnswer
        )

        return currentQuestion
    }

    fun checkAnswer(userAnswerIndex: Int?): Boolean {

        return currentQuestion?.let { question ->
            val correctAnswerId = question.variants.indexOf(question.correctAnswer)
            if (correctAnswerId == userAnswerIndex) {
                question.correctAnswer.learned = true
                true
            } else {
                false
            }
        } ?: false
    }
}

const val NUMBER_OF_ANSWERS: Int = 4
