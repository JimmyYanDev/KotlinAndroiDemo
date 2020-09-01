package com.qmyan.demo.uipractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.qmyan.demo.R

class MessageAdapter(val dataList: ArrayList<Message>) : RecyclerView.Adapter<MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            Message.MSG_TYPE_RECIVE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_left_msg, parent, false)
                LeftViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_right_msg, parent, false)
                RightViewHolder(view)
            }
        }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val msg = dataList[position]
        when (holder) {
            is LeftViewHolder -> {
                holder.msgText.text = msg.content
            }
            is RightViewHolder -> {
                holder.msgText.text = msg.content
            }
        }
    }

    override fun getItemViewType(position: Int) = dataList[position].type

}