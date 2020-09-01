package com.qmyan.demo.uipractice

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.qmyan.demo.R

sealed class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class LeftViewHolder(itemView: View) : MessageViewHolder(itemView) {
    val msgText = itemView.findViewById<TextView>(R.id.tv_left_msg)
}

class RightViewHolder(itemView: View) : MessageViewHolder(itemView) {
    val msgText = itemView.findViewById<TextView>(R.id.tv_right_msg)
}