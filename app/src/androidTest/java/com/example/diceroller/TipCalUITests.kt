package com.example.diceroller

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performTextInput
import com.example.diceroller.ui.theme.DiceRollerTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipCalUITests {


    @JvmField
    @Rule
    val composeTestRule= createComposeRule()


    @Test
    fun calculate_50_percent_tip(){
        composeTestRule.setContent {
            DiceRollerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    tipCalculator()
                }
            }
        }

        composeTestRule.onNodeWithText("Bill Amount").performTextInput("100")
        composeTestRule.onNodeWithText("Tip Percentage %").performTextInput("50")
        val expectedTip= NumberFormat.getCurrencyInstance().format(50)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists(
            "No node with this text was found."
        )
    }


}