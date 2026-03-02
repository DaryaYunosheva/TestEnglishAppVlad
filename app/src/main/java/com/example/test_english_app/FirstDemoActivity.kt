package com.example.test_english_app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test_english_app.databinding.ActivityFirstDemoBinding
import androidx.core.net.toUri

class FirstDemoActivity : AppCompatActivity(){

    private lateinit var binding: ActivityFirstDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            val intent = Intent(this@FirstDemoActivity, SecondDemoActivity::class.java)
            intent.putExtra("EXTRA_KEY_TEXT", "Выбирай уровень и погнали")
            startActivity(intent)

        }
        binding.btnVlad.setOnClickListener {
            val urlVlad = "https://vk.ru/id325629066"
            val intent = Intent(Intent.ACTION_VIEW, urlVlad.toUri())
            startActivity(intent)
        }
        binding.btnDarya.setOnClickListener {
            val urlDarya = "https://vk.ru/vinbele"
            val intent = Intent(Intent.ACTION_VIEW, urlDarya.toUri())
            startActivity(intent)
        }
    }

}