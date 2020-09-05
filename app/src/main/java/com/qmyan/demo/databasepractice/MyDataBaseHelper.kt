package com.qmyan.demo.databasepractice

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MyDataBaseHelper(
    context: Context,
    name: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    companion object {
        private const val TAG = "MyDataBaseHelper"
        const val TABLE_NAME_BOOK = "book"
        const val TABLE_NAME_CATEGORY = "category"
        private const val CREATE_TABLE_BOOK = "create table $TABLE_NAME_BOOK (" +
                "id integer primary key autoincrement," +
                "name text," +
                "author text," +
                "pages integer," +
                "price real," +
                "category_id integer)"
        private const val CREATE_TABLE_CATEGORY = "create table $TABLE_NAME_CATEGORY (" +
                "id integer primary key autoincrement," +
                "name text," +
                "code integer)"
        private const val ALTER_TABLE_BOOK = "alter table book add column category_id integer"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_BOOK)
        db?.execSQL(CREATE_TABLE_CATEGORY)
        Log.i(TAG, "onCreate: CREATE_TABLE_BOOK")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.i(TAG, "onUpgrade: oldVersion $oldVersion newVersion $newVersion")
        if (oldVersion == 1) {
            db?.execSQL(CREATE_TABLE_CATEGORY)
        }
        if (oldVersion == 2) {
            db?.execSQL(ALTER_TABLE_BOOK)
        }
    }
}