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
import com.example.trafikkskilt.models.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main()
        }
    }
}

@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@ExperimentalMaterialApi
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
                StartView(navController = navController)
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

            composable(route = "infoView"){
                InfoView(navController = navController)
            }
        }
    }
}
