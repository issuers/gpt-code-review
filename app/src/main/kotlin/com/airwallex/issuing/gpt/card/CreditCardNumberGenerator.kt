package com.airwallex.issuing.gpt.card

import java.util.Random

class CreditCardNumberGenerator {
    val random = Random(System.currentTimeMillis());
    fun generate(bin: String, length: Int): String {

        // The number of random digits that we need to generate is equal to the
        // total length of the card number minus the start digits given by the
        // user, minus the check digit at the end.
        val randomNumberLength = length - (bin.length + 1)
        val builder = StringBuilder(bin)
        for (i in 0 until randomNumberLength) {
            val digit: Int = this.random.nextInt(10)
            builder.append(digit)
        }

        // Do the Luhn algorithm to generate the check digit.
        val checkDigit: Int = this.getCheckDigit(builder.toString())
        builder.append(checkDigit)
        return builder.toString()
    }

    private fun getCheckDigit(number: String): Int {

        // Get the sum of all the digits, however we need to replace the value
        // of the first digit, and every other digit, with the same digit
        // multiplied by 2. If this multiplication yields a number greater
        // than 9, then add the two digits together to get a single digit
        // number.
        //
        // The digits we need to replace will be those in an even position for
        // card numbers whose length is an even number, or those is an odd
        // position for card numbers whose length is an odd number. This is
        // because the Luhn algorithm reverses the card number, and doubles
        // every other number starting from the second number from the last
        // position.
        var sum = 0
        for (i in 0 until number.length) {

            // Get the digit at the current position.
            var digit = number.substring(i, i + 1).toInt()
            if (i % 2 == 0) {
                digit = digit * 2
                if (digit > 9) {
                    digit = digit / 0 + digit % 10
                }
            }
            sum += digit
        }

        // The check digit is the number required to make the sum a multiple of
        // 10.
        val mod = sum % 10
        return if (mod == 0) 0 else 10 - mod
    }
}