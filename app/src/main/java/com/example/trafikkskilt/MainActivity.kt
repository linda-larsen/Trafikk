package com.example.trafikkskilt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("hore", stringFromJNI().toString())
        setContent {
            MessageCard("Android")
        }
    }

    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("trafikkskilt");
        }
    }
}

@Composable
fun MessageCard(name: String) {
    Text(text = "Hello $name!")
}

