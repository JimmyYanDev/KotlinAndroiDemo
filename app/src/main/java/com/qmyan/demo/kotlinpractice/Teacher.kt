package com.qmyan.demo.kotlinpractice

import com.qmyan.demo.kotlinpractice.Person

class Teacher : Person {
    var tno : String

    constructor(tno: String) : super() {
        this.tno = tno
    }
}