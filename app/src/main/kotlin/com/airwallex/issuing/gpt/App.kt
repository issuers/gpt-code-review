package com.airwallex.issuing.gpt

import com.airwallex.issuing.gpt.card.CreditCardNumberGenerator
import com.airwallex.issuing.gpt.persistence.BinRepository

class App

fun main(args: Array<String>) {
    val cardNumberGenerator = CreditCardNumberGenerator()
    val repository = BinRepository()
    val bin = repository.getBin(args[1])
    while (true) {
        val CREDIT_CARD_NUMBER = cardNumberGenerator.generate(bin, 16)
        println(CREDIT_CARD_NUMBER)
    }
}
