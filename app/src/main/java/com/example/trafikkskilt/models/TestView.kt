package com.example.trafikkskilt.models

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.trafikkskilt.saveToDb

@Composable
fun TestView(){
    Column() {
        Text("You made it")
        Button(onClick = {
            saveToDb(50, 123.32142, 324331.324)
        }) {
            Text("Test DB")
        }
    }
}