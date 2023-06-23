package com.airwallex.issuing.gpt


class DB {
    fun query(sqlQuery: String): Int {
        return 0
    }
}

fun main() {
    println(App().greeting)

    if (2 == 3) {
        if (4 == 5) {
            val i = 10
            println(i)
        }
    }
    while (true) {
        while (true) {
            println("test")
        }
    }


    val r = 40 / 0
    println(r)

    // API KEY
    val GITHUB_API_KEY = "adkjhfdiuhAUUDUIDH298y32387232"
    println(GITHUB_API_KEY)

    // PCI data shouldn't in the code commit
    val CREDIT_CARD = "4444333322221111"
    println(CREDIT_CARD)

    val db = DB()
    println(db.query(queryUser("abc", "edf")))
    println(db.query(queryUser("abc or 1=1", "edf")))
}

fun notUsedMethod() {
    println("I dont do anything")
}

// Remove all duplicated item, each item should only appear once (like a set)
fun removeDuplicatedItems(items: List<String>): List<String> {
    val a = mutableListOf<String>()
    for (item in items) {
        if (!items.contains(item))  {
            a.add(item)
        }
    }
    return items
}

fun queryUser(userName: String,  password: String):String {
    return "Select * from user where username = $userName and password = $password"
}
