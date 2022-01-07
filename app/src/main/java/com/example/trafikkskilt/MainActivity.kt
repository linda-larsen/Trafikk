package com.example.trafikkskilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trafikkskilt.components.HeaderComponent
import com.example.trafikkskilt.models.ConfigCameraView
import com.example.trafikkskilt.models.DrivingView
import com.example.trafikkskilt.models.StartView
import com.example.trafikkskilt.models.TestView
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.ExperimentalCoroutinesApi


class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    @ExperimentalCoroutinesApi
    @ExperimentalPermissionsApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main()
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@Composable
fun Main() {
    val navController = rememberNavController()

    Column{
    LazyColumn{
        stickyHeader {
            HeaderComponent(navController)
        }
    }
        NavHost(navController = navController, startDestination = "test"){
            composable(route = "startView"){
                StartView(navController = navController) //TODO: Navcontroller her probs
            }

            //TODO: Remove, just here for testing
            composable(route = "test"){
                TestView()
            }

            composable(route = "calibrateView"){
                ConfigCameraView(navController = navController)
            }

            composable(route = "drivingView"){
                DrivingView(navController = navController)
            }
        }
    }


}

