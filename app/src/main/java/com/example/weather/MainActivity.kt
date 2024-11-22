package com.example.weather

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val input = findViewById<EditText>(R.id.city)
        val but: Button = findViewById(R.id.apply)
        val dataTime: TextView = findViewById(R.id.DATATIME)
        val selectedCountry: TextView = findViewById(R.id.textView)
        val town: TextView = findViewById(R.id.textView2)
        val state: TextView = findViewById(R.id.textView3)
        val temperature: TextView = findViewById(R.id.textView4)
        val humidity: TextView = findViewById(R.id.textView5)
        val minTemp: TextView = findViewById(R.id.textView6)
        val maxTemp: TextView = findViewById(R.id.textView7)

        but.setOnClickListener {
            val inputText = input.text.toString()
            dataTime.text = inputText
        }
    }
}