package com.example.neurochat

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBChats(private val context :Context, private val cursor: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = buildString {
        append("CREATE TABLE IF NOT EXISTS chats (chat INTEGER PRIMARY KEY, chat_name TEXT)")
    }
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}