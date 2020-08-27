package com.qmyan.demo

class Student(var sno : String, var grade : Double) : Person(), Study {
    init {
        println("new student")
    }

    constructor() : this("", 0.0)

    override fun readBooks() {
        println("$name is reading")
    }

    override fun doHomeworks() {
        super.doHomeworks()
        println("my name is $name")
    }
}