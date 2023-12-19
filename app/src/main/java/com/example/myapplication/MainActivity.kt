package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlin.math.absoluteValue
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    unitConvertor()
                }
            }
        }
    }
}


@Composable
fun unitConvertor(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var iExpand by remember { mutableStateOf(false) }
    var oExpand by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }

    fun convertUnits(){

        // ?: - elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

        Column(

        modifier = Modifier.fillMaxSize(),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally


    ){

        Text(text = "Unit Convertor")

       Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = inputValue,
                onValueChange = {
                    inputValue = it
                    convertUnits()
                    // Here goes what should happen, when the Value of our OutlinedTextField changes
                },
                label = {Text("Enter Value") })

        Spacer(modifier = Modifier.height(16.dp))
        Row {

            Box {

                Button(onClick = {iExpand=true }) {

                    Text(text = inputUnit)

                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")

                }

                DropdownMenu(expanded = iExpand, onDismissRequest = { iExpand=false }) {

                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") }, onClick = {

                            iExpand = false
                            inputUnit = "Centimeters"
                            conversionFactor.value  = 0.01
                            convertUnits()
                        })


                    DropdownMenuItem(
                        text = { Text(text = "Meters") }, onClick = {

                            iExpand = false
                            inputUnit = "Meters"
                            conversionFactor.value  = 1.0
                            convertUnits()
                        })

                    DropdownMenuItem(
                        text = { Text(text = "Feet") }, onClick = {
                            iExpand = false
                            inputUnit = "Feet"
                            conversionFactor.value  = 0.3048
                            convertUnits()
                        })

                    DropdownMenuItem(
                        text = { Text(text = "Milimeter") }, onClick = {

                            iExpand = false
                            inputUnit = "Millimeters"
                            conversionFactor.value  = 0.001
                            convertUnits()
                        })

                }


            }



            Box {

                Button(onClick = { oExpand =true}) {

                    Text(text = outputUnit)

                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")

                }

                DropdownMenu(expanded = oExpand, onDismissRequest = { oExpand = false }) {

                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") }, onClick = {

                            oExpand = false
                            outputUnit = "Centimeters"
                            oConversionFactor.value  = 0.01
                            convertUnits()
                        })


                    DropdownMenuItem(
                        text = { Text(text = "Meters") }, onClick = {

                            oExpand = false
                            outputUnit = "Meters"
                            oConversionFactor.value  = 1.0
                            convertUnits()

                             })

                    DropdownMenuItem(
                        text = { Text(text = "Feet") }, onClick = {

                            oExpand = false
                            outputUnit = "Feet"
                            oConversionFactor.value  = 0.3048
                            convertUnits()
                        })

                    DropdownMenuItem(
                        text = { Text(text = "Milimeter") }, onClick = {

                            oExpand = false
                            outputUnit = "Millimeter"
                            conversionFactor.value  = 0.001
                            convertUnits()
                        })

                }
            }
        }

            Spacer(modifier = Modifier.height(16.dp))
            // Result Text
            Text("Result: $outputValue $outputUnit",
            )
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        unitConvertor()
    }
}