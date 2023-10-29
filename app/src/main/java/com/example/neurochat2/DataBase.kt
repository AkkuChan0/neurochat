//package com.example.neurochat2
//
//import android.content.ContentValues
//import android.content.Context
//import android.database.Cursor
//import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.SQLiteOpenHelper
//
//class DataBase(private val context: Context, private val factory: SQLiteDatabase.CursorFactory?)
//    : SQLiteOpenHelper(context, "data_base", factory, 1) {
//
//    override fun onCreate(db: SQLiteDatabase?) {
//        val query = "CREATE TABLE IF NOT EXISTS context (id INT PR, role TEXT, message TEXT)"
//        db!!.execSQL(query)
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
//        db!!.execSQL("DROP TABLE IF EXISTS context")
//        onCreate(db)
//    }
//
//    fun saveContext(role: String, text: String) {
//        val values = ContentValues()
//        var db = this.readableDatabase
//        val result = db.rawQuery("SELECT * FROM context", null)
//        val id = result.count + 1
//        values.put("id", id)
//        values.put("role", role)
//        values.put("text", text)
//
//        db = this.writableDatabase
//        db.insert("context", null, values)
//
//        db.close()
//    }
//
//    fun loadContext() {
//        val db = this.readableDatabase
//
//        val result = db.rawQuery("SELECT * FROM context", null)
//        var context = mutableListOf<Map<String, String>>()
//    }
//}