package com.qmyan.demo.contentproviderpractice

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qmyan.demo.R
import com.qmyan.demo.databasepractice.MyDataBaseHelper
import kotlinx.android.synthetic.main.activity_data_base_demo.*

class ContentProviderDemoActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private const val TAG = "ContentProviderDemo"

        fun actionStart(context: Context) {
            context.startActivity(Intent(context, ContentProviderDemoActivity::class.java))
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider_demo)

        btn_insert.setOnClickListener(this)
        btn_update.setOnClickListener(this)
        btn_delete.setOnClickListener(this)
        btn_query.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_insert -> {
                Toast.makeText(this, "btn_insert", Toast.LENGTH_SHORT).show()
            }
            R.id.btn_update -> {
                Toast.makeText(this, "btn_update", Toast.LENGTH_SHORT).show()
            }
            R.id.btn_delete -> {
                Toast.makeText(this, "btn_delete", Toast.LENGTH_SHORT).show()
            }
            R.id.btn_query -> {
                val cursor = contentResolver.query(
                    Uri.parse("content://${MyContentProvider.AUTHORITY}/${MyDataBaseHelper.TABLE_NAME_BOOK}/${(1..40).random()}"),
                    null,
                    null,
                    null,
                    null
                )
                while (cursor?.moveToNext() == true) {
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Toast.makeText(this, "Book($id, $name, $author, $pages, $price)", Toast.LENGTH_SHORT).show()
                    Log.i(TAG, "onClick: btn_query Book($id, $name, $author, $pages, $price)")
                }
            }
        }
    }

}