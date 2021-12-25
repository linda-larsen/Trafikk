package com.example.trafikkskilt

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trafikkskilt.components.HeaderComponent
import com.example.trafikkskilt.models.ConfigCameraView
import com.example.trafikkskilt.models.StartView
import com.example.trafikkskilt.models.TestView
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

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

@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@Composable
fun Main() {
    val navController = rememberNavController()
    Column() {
    LazyColumn(){
        stickyHeader {
            HeaderComponent(navController)
        }
    }
        NavHost(navController = navController, startDestination = "startView"){
            composable(route = "startView"){
                StartView() //TODO: Navcontroller her probs
            }

            //TODO: Remove, just here for testing
            composable(route = "test"){
                TestView()
            }

            composable(route = "calibrateView"){
                ConfigCameraView(navController = navController)
            }
        }
    }


}

