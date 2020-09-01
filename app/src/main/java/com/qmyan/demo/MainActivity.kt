package com.qmyan.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qmyan.demo.uipractice.SMSDemoActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SMSDemoActivity.actionStart(this)
    }
}