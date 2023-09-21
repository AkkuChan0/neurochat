package com.example.neurochat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainChat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val textBox: TextView = findViewById(R.id.textView)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_chat)
    }
}