package com.example.todolist.app

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, "todo.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE todo(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    fun insertData(title: String) {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put("title", title)
        db.insert("todo", null, cv)
    }

    fun getAllData(): List<String> {
        val list = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM todo", null)

        while (cursor.moveToNext()) {
            list.add(cursor.getString(1))
        }
        cursor.close()
        return list
    }

    fun deleteData(title: String) {
        val db = writableDatabase
        db.delete("todo", "title=?", arrayOf(title))
    }
}