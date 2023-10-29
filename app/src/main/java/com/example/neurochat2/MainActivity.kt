package com.example.neurochat2

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var messageHistory: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var bot: OpenBot
    private lateinit var itemMessage: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.app_bar_main)
        messageHistory = findViewById(R.id.recyclerView)
        messageBox = findViewById(R.id.messageText)
        itemMessage = mutableListOf()
        bot = OpenBot(messageHistory)

        messageHistory.layoutManager = LinearLayoutManager(this)
    }

    fun sendMessage(view: View) {
        val indicateNeuro = findViewById<TextView>(R.id.indicateNeuro)
        lifecycleScope.launch {
            indicateNeuro.text = "Нейро отвечает..."
            bot.getResponse(messageBox.text.toString())
            indicateNeuro.text = ""
        }
        messageBox.text.clear()
    }

}