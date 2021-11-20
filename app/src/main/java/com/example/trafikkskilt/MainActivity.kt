package com.example.trafikkskilt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.trafikkskilt.cameraComponents.CameraCapture
import com.example.trafikkskilt.components.HeaderComponent
import com.google.accompanist.permissions.ExperimentalPermissionsApi

class MainActivity : ComponentActivity() {

    @ExperimentalPermissionsApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("hore", stringFromJNI().toString())
        setContent {

            Main()
        }
    }

    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("trafikkskilt");
        }
    }
}

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@Composable
fun Main() {
    Column {
    LazyColumn(){
        stickyHeader {
            HeaderComponent()
        }
    }
        CameraCapture()
    //StartView()
    }


}

