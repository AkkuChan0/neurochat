package com.example.neurochat2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
    }

    fun returnToMainActivity(view: View) {
        //setContentView(R.layout.app_bar_main)
        startActivity(Intent(this, MainActivity::class.java))
    }
    fun clearSystemPrompt(view: View) {
        bot.clearMessage()
        Toast.makeText(applicationContext, "System prompt очищен", Toast.LENGTH_SHORT).show()

    }
    fun clearAllHistory(view: View) {
        bot.clearMessage()
        Toast.makeText(applicationContext, "История очищена", Toast.LENGTH_SHORT).show()
    }

}