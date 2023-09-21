package com.example.diceroller

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {

    @Test
    fun calculateTip50PercentNoRoundUp(){
        val amount = 100.00
        val tipPercent = 50.00
        val expectedTip= NumberFormat.getCurrencyInstance().format(50)
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, false)

        assertEquals(expectedTip, actualTip)
    }
}