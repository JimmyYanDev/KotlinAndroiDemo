package com.qmyan.demo.uipractice

data class Message(val content: String, val type: Int) {
    companion object {
        const val MSG_TYPE_SEND = 0
        const val MSG_TYPE_RECIVE = 1
    }
}
