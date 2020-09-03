package com.qmyan.demo.kotlinpractice

import kotlin.math.max

var globalString : String? = null

fun main() {
    /* basic
    println("Hello Kotlin!")

    val a = 10
    val b = 5
    var c: Int
    c = a * b
    println("c = " + c)

    println("a > b ? " + (largeNumber2(a, b) == a))

    getScore2("Tom")
    getScore2("Toom")

    checkNumber(12)
    checkNumber(1.2)
    checkNumber(1L)

    forDemo()
    */

    /*
    // class
    val p = Person()
    p.name = "Li"
    p.age = 12
    p.eat()
    val t = Teacher("001")
    t.name = "Tim"
    t.age = 30
    t.eat()
    // interface
    study(Student())
    // data class
    println(Book("first code", "abf3343").toString())
    // singleton
    Singleton.toast()
    */

    // collection
    var list = listOf("apple", "orange", "banana", "pear", "graper")
    for (item in list) println(item)
    // invalid, default is inmutable
    // list.add("test");
    var mutableList = mutableListOf("apple", "orange", "banana", "pear", "graper")
    println("mutableList original size is ${mutableList.size}")
    mutableList.add("extra1")
    mutableList.add("extra2")
    println("mutableList size after modified is ${mutableList.size}")
    var set = setOf("apple", "orange", "banana", "pear", "graper")
    var map = mapOf(1 to "apple", 2 to "banana", 3 to "orange")
    for ((key, value) in map) {
        println("${key}.$value")
    }
    var mutableMap = HashMap<Int, String>()
    mutableMap[1] = "apple"
    mutableMap[2] = "orange"
    mutableMap[3] = "banana"
    for (key in mutableList.indices) {
        println("KEY:$key,VAL:${mutableList[key]}")
    }
    // Lambda
    val lambda = {a : Int, b : Int ->{
        a > b
    }}
    println("1 > 3 ? ${lambda(1, 3)()}")
    list.filter { it.length > 5 }
        .map { it.toUpperCase() }
        .let { println(it) }
    // null detect
    var str : String? = null
    println("Input String length is ${getTexLength(
        str
    )}")
    str = "1234567"
    println("Input String length is ${getTexLength2(
        str
    )}")
    if (null != str) {
        getTexLength3(str)
    }
    str?.length
    str?.toUpperCase()
    if (null != globalString) {
        globalString!!.length
    }
    globalString?.length
    // fun default value
    largeNumber()
    largeNumber(2)
    largeNumber(b = 3)
}

// null detect
fun getTexLength(text: String?) = if (null != text) text.length else 0

fun getTexLength2(text: String?) = text?.length ?: 0

fun getTexLength3(text: String?) = text!!.length

// data class
data class Book(val name: String, val no : String)

// singleton
object Singleton {
    fun toast() {
        println("single single singleton")
    }
}

fun study(st: Study) {
    st.readBooks()
    st.doHomeworks()
}

/*
fun largeNumber(a : Int, b : Int) : Int {
    return max(a, b)
}
*/
// simplefied

fun largeNumber(a: Int = 1, b: Int = 2) = max(a, b)

fun largeNumber2(a: Int = 3, b: Int) = if (a > b) a else b

fun getScore(name : String) = when (name) {
    "Tom" -> 80
    "Leo" -> 90
    "Cuke" -> 99
    else -> {
        println("unknown")
    }
}

fun getScore2(name : String) = when {
    name == "Tom" -> {
        println("80")
        80
    }
    name == "Leo" -> 90
    name == "Cuke" -> 99
    name.startsWith("Ja") -> 66
    else -> {
        println("unknown")
    }
}

fun checkNumber(n: Number) = when (n) {
    is Int -> println("Int")
    is Double -> println("Double")
    else -> {
        println("unknown")
        println()
    }
}

fun forDemo() {
    // [0,10]
    println("闭区间")
    for (i in 0..10) print("$i \t")
    println()
    // [0,10)
    println("半闭半开区间")
    for (i in 0 until 10) print("$i \t")
    println()
    // step
    println("step")
    for (i in 0..10 step 2) print("$i \t")
    println()
    // 降序区间
    println("降序区间")
    for (i in 10 downTo 0 step 1) print("$i \t")
    println()
}