package com.example.trafikkskilt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.trafikkskilt.models.StartView

class MainActivity : ComponentActivity() {
    private val startViewActivity by viewModels<StartView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("hore", stringFromJNI().toString())
        setContent {
            Column {
                MessageCard("Android")
                startViewActivity.startView()            }
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

