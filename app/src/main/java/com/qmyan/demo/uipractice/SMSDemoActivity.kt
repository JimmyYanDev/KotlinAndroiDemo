package com.qmyan.demo.uipractice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmyan.demo.R
import kotlinx.android.synthetic.main.activity_sms_demo.*

class SMSDemoActivity : AppCompatActivity() {
    private val dataList = ArrayList<Message>().apply {
        add(Message("HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello!", Message.MSG_TYPE_RECIVE))
        add(Message("HiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHi!", Message.MSG_TYPE_SEND))
        add(Message("HowHowHowHowHowHowHowHowHowHowHowHowHowHowHowHowHowHow are you?", Message.MSG_TYPE_RECIVE))
        add(Message("I am fine, thank you.", Message.MSG_TYPE_SEND))
    }

    private val adapter = MessageAdapter(dataList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms_demo)

        init()
    }

    private fun init() {
        rc_msg_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rc_msg_list.adapter = adapter
        btn_send.setOnClickListener {
            val content = et_msg_box.text.toString()
            if (!content.isEmpty()) {
                dataList.add(Message(content, Message.MSG_TYPE_SEND))
                et_msg_box.text.clear()
                adapter.notifyItemInserted(dataList.size - 1)
                rc_msg_list.scrollToPosition(dataList.size - 1)
            } else {
                Toast.makeText(this, "Please input some message first!!!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, SMSDemoActivity::class.java)
            context.startActivity(intent)
        }
    }
}