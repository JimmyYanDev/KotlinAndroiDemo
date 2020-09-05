package com.qmyan.demo.databasepractice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import com.qmyan.demo.R
import kotlinx.android.synthetic.main.activity_data_base_demo.*

class DataBaseDemoActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private const val TAG = "DataBaseDemoActivity"

        fun actionStart(context: Context) {
            context.startActivity(Intent(context, DataBaseDemoActivity::class.java))
        }

    }

    private val mDataBase by lazy {
        MyDataBaseHelper(this, "demo", null, 3).writableDatabase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_base_demo)

        btn_create.setOnClickListener(this)
        btn_insert.setOnClickListener(this)
        btn_update.setOnClickListener(this)
        btn_delete.setOnClickListener(this)
        btn_query.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_create -> {
                Log.i(TAG, "onClick: btn_create $mDataBase.isOpen")
            }
            R.id.btn_insert -> {
                val sed = (1..10).random()
                val row = mDataBase.insert(
                    MyDataBaseHelper.TABLE_NAME_BOOK,
                    null,
                    contentValuesOf(
                        "name" to "book${sed}",
                        "author" to "author${sed}",
                        "pages" to (100 * sed),
                        "price" to (10.0 * sed)
                    )
                )
                Log.i(TAG, "onClick: btn_insert effect rows $row")
            }
            R.id.btn_update -> {
                val sed = (1..10).random()
                val row = mDataBase.update(
                    MyDataBaseHelper.TABLE_NAME_BOOK, contentValuesOf(
                        "name" to "book${sed}",
                        "author" to "author${sed}",
                        "pages" to (100 * sed),
                        "price" to (10.0 * sed)
                    ), "id = ?", arrayOf("$sed")
                )
                Log.i(TAG, "onClick: btn_insert effect sed $sed")
                Log.i(TAG, "onClick: btn_insert effect rows $row")
            }
            R.id.btn_delete -> {
                val sed = (1..10).random()
                val row = mDataBase.delete(MyDataBaseHelper.TABLE_NAME_BOOK, "id = ?", arrayOf("$sed"))
                Log.i(TAG, "onClick: btn_insert effect sed $sed")
                Log.i(TAG, "onClick: btn_insert effect rows $row")
            }
            R.id.btn_query -> {
                val cursor = mDataBase.query(
                    MyDataBaseHelper.TABLE_NAME_BOOK,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.i(TAG, "onClick: btn_query Book($id, $name, $author, $pages, $price)")
                }
            }
        }
    }
}