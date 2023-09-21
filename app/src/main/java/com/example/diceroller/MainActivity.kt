@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceRollerApp()
                }
            }
        }
    }
}

@Composable
fun DiceWithButton(modifier: Modifier= Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center))
{
    var result by remember { mutableStateOf(1) }
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally)
    {

        Image(painter = painterResource(id = imageResource), contentDescription = result.toString())
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            result=(1..6).random()
        }) {
            Text(text = "Roll")

        }

    }

}



@Composable
fun tipCalculator(modifier: Modifier= Modifier
    .fillMaxSize())
    {
        var amountInput by rememberSaveable { mutableStateOf("") }
        val amount = amountInput.toDoubleOrNull() ?: 0.0
        var percentInput by rememberSaveable { mutableStateOf("") }
        val percentAmount = percentInput.toDoubleOrNull() ?: 0.0
        var roundUp by rememberSaveable { mutableStateOf(false) }
        var tip = calculateTip(amount,percentAmount,roundUp)
        Column(modifier = modifier
        .padding(30.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.Start)
    {

        Text(text = "Calculate Tip",
        fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
       )
        EditNumberField(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),amountInput,"Bill Amount",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )) {
            amountInput = it
        }

        EditNumberField(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),percentInput,"Tip Percentage %",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )) {
            percentInput = it
        }

        RoundTipRow(isRounded = roundUp, onRoundChanged = {roundUp=it}
        )

        Text(text = stringResource(id = R.string.tip_amount,tip),
            fontWeight = FontWeight.Bold,
            style=MaterialTheme.typography.displaySmall,
            modifier = Modifier.align(Alignment.Start)
        )


        }
    }

@Composable
fun EditNumberField(modifier: Modifier = Modifier,value: String,label:String="",keyboardOptions: KeyboardOptions,onValueChange:(String)->Unit) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label={Text(label)},
        keyboardOptions=keyboardOptions,)
}

@Composable
fun RoundTipRow(modifier: Modifier=Modifier,isRounded:Boolean,onRoundChanged:(Boolean)->Unit){
    Row( modifier = modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp), horizontalArrangement = Arrangement.SpaceAround,) {
        Text(text = "Round Up Tip?",
        textAlign = TextAlign.Center,
        modifier=Modifier.align(alignment = Alignment.CenterVertically))
        Switch(checked =isRounded , onCheckedChange =onRoundChanged
        ,modifier= Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End))
    }
}
@VisibleForTesting
 fun calculateTip(amount: Double, tipPercent: Double = 15.0,roundUp:Boolean): String {
    var tip = tipPercent / 100 * amount
    if (roundUp)
        tip = kotlin.math.ceil(tip)

    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun DiceRollerApp() {
    DiceRollerTheme {
       // DiceWithButton()
       tipCalculator()
    }
}