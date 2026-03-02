package com.example.test_english_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test_english_app.databinding.ActivityFirstDemoBinding
import com.example.test_english_app.databinding.ActivitySecondBinding
import com.example.test_english_app.databinding.ActivitySecondDemoBinding

class SecondDemoActivity : AppCompatActivity(){

    private lateinit var binding: ActivitySecondDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnBackToStart.setOnClickListener {
                val intent = Intent(this@SecondDemoActivity , FirstDemoActivity::class.java)
                startActivity(intent)
            }

            btnA1.setOnClickListener {
                val intent = Intent(this@SecondDemoActivity, MainActivity::class.java)
                startActivity(intent)
            }
            val text = intent.getStringExtra("EXTRA_KEY_TEXT")
            tvStartInformation.text = text
        }
    }

}