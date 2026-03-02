//        findViewById
//        ViewBinding
//
//        val tvQuestionWorld: TextView = findViewById(R.id.tvQuestionWord)
//
//        tvQuestionWorld.text = "42"
//        tvQuestionWorld.setTextColor(Color.BLUE)
//        tvQuestionWorld.setTextColor(Color.parseColor("#FDD600"))
//        binding.tvQuestionWord.text = "Да есть же"
//        binding.tvQuestionWord.setTextColor(Color.MAGENTA)

package com.example.test_english_app

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.test_english_app.databinding.ActivityLearnWorldBinding
import kotlin.time.ComparableTimeMark

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityLearnWorldBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityLearnWorldBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLearnWorldBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val trainer = LearnWordsTrainer()
        showNextQuestion(trainer)

        binding.ibClose.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondDemoActivity::class.java)
            startActivity(intent)

        }
        with(binding) {
            btnContinue.setOnClickListener {
                layoutResult.isVisible = false
                markAnswerNeutral(layoutAnswer1, tvVariantNumber1, tvVariantValye1)
                markAnswerNeutral(layoutAnswer2, tvVariantNumber2, tvVariantValye2)
                markAnswerNeutral(layoutAnswer3, tvVariantNumber3, tvVariantValye3)
                markAnswerNeutral(layoutAnswer4, tvVariantNumber4, tvVariantValye4)
                showNextQuestion(trainer)
            }
            btnSkip.setOnClickListener {
                showNextQuestion(trainer)
            }
        }

     }


    private fun showNextQuestion(trainer: LearnWordsTrainer) {
        val firstQuestion: Question? = trainer.getNextQuestion()
        with(binding) {
            if (firstQuestion == null || firstQuestion.variants.size < NUMBER_OF_ANSWERS) {
                tvQuestionWord.isVisible = false
                layoutVariants.isVisible = false
                btnSkip.text = "Complete! Ты лев!"
            } else {
                btnSkip.isVisible = true
                tvQuestionWord.isVisible = true
                tvQuestionWord.text = firstQuestion.correctAnswer.original

                tvVariantValye1.text = firstQuestion.variants[0].translate
                tvVariantValye2.text = firstQuestion.variants[1].translate
                tvVariantValye3.text = firstQuestion.variants[2].translate
                tvVariantValye4.text = firstQuestion.variants[3].translate

                layoutAnswer1.setOnClickListener {
                    if (trainer.checkAnswer(0)) {
                        markAnswerCorrect(layoutAnswer1, tvVariantNumber1, tvVariantValye1)
                        showResultMassage(true)
                    } else {
                        markAnswerWrong(layoutAnswer1, tvVariantNumber1, tvVariantValye1)
                        showResultMassage(false)
                    }
                }
                layoutAnswer2.setOnClickListener {
                    if (trainer.checkAnswer(1)) {
                        markAnswerCorrect(layoutAnswer2, tvVariantNumber2, tvVariantValye2)
                        showResultMassage(true)
                    } else {
                        markAnswerWrong(layoutAnswer2, tvVariantNumber2, tvVariantValye2)
                        showResultMassage(false)
                    }
                }
                layoutAnswer3.setOnClickListener {
                    if (trainer.checkAnswer(2)) {
                        markAnswerCorrect(layoutAnswer3, tvVariantNumber3, tvVariantValye3)
                        showResultMassage(true)
                    } else {
                        markAnswerWrong(layoutAnswer3, tvVariantNumber3, tvVariantValye3)
                        showResultMassage(false)
                    }
                }
                layoutAnswer4.setOnClickListener {
                    if (trainer.checkAnswer(3)) {
                        markAnswerCorrect(layoutAnswer4, tvVariantNumber4, tvVariantValye4)
                        showResultMassage(true)
                    } else {
                        markAnswerWrong(layoutAnswer4, tvVariantNumber4, tvVariantValye4)
                        showResultMassage(false)
                    }
                }
            }
        }
    }


    private fun  markAnswerNeutral(
        layoutAnswer: LinearLayout,
        tvVariantNumber: TextView,
        tvVariantValye: TextView,
    ) {


        layoutAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_round_containers
        )


        tvVariantNumber.apply {
            background = ContextCompat.getDrawable(
                this@MainActivity,
                R.drawable.shape_round_variants,
            )
            setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.textVariantsColor
                )
            )
        }

        tvVariantValye.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.textVariantsColor
            )
        )

    }



    private fun markAnswerWrong(
        layoutAnswer: LinearLayout,
        tvVariantNumber: TextView,
        tvVariantValye: TextView,
    ){
        layoutAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_round_containers_wrong
        )
        tvVariantNumber.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_round_variants_wrong
        )

        tvVariantNumber.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.white
            )
        )
        tvVariantValye.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.wrongAnswerColor
            )
        )

    }


    private fun markAnswerCorrect(
        layoutAnswer: LinearLayout,
        tvVariantNumber: TextView,
        tvVariantValye: TextView,
    ) {
        layoutAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_round_containers_correct
        )
        tvVariantNumber.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_round_variants_correct
        )

        tvVariantNumber.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.white
            )
        )
        tvVariantValye.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.correctAnswerColor
            )
        )

    }

    private fun showResultMassage(isCorrect: Boolean) {
        val color: Int
        val messageText: String
        val resultIconResource: Int
        if (isCorrect) {
            color = ContextCompat.getColor(this, R.color.correctAnswerColor)
            resultIconResource = R.drawable.ic_correct
            messageText = "Correct!"
        } else {
            color = ContextCompat.getColor(this, R.color.wrongAnswerColor)
            resultIconResource = R.drawable.ic_wrong
            messageText = "Wrong!"

        }
        with(binding) {
            btnSkip.isVisible = false
            layoutResult.isVisible = true
            btnContinue.setTextColor(color)
            layoutResult.setBackgroundColor(color)
            tvResultMassege.text = messageText
            ivResultIcon.setImageResource(resultIconResource)

        }
    }
}