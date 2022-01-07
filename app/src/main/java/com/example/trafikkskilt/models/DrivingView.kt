package com.example.trafikkskilt.models

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.example.trafikkskilt.R
import com.example.trafikkskilt.constants.paddingSize
import com.example.trafikkskilt.constants.textSize
import com.google.android.gms.location.FusedLocationProviderClient

@Composable
fun DrivingView(navController: NavController, locationProvider: FusedLocationProviderClient){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = stringResource(id = R.string.speedlimit_is),
            Modifier
                .padding(paddingSize * 2)
                .fillMaxHeight()
                .weight(1f),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Box (
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxHeight().weight(2f),
        ) {
            Image(
                painterResource(R.drawable.emptysign),
                contentDescription = stringResource(id = R.string.app_intro_image_content_description),
                modifier = Modifier
                    .size(200.dp)
                    .padding(paddingSize)
                    .fillMaxHeight(),
            )
            Text(
                text = speedLimit(),
                fontSize = 100.sp,
                modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 0.dp)

            )
        }

        Button(
            onClick = {
            //TODO: Start camera
            navController.navigate("startView")
            },
            modifier = Modifier
                .padding(paddingSize*3)
                .height(60.dp)
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.button_color)),

        ) {
            Text(
                text = stringResource(id = R.string.done),
                fontSize = textSize*2
                )
        }

    }
}

fun speedLimit():String{
    //TODO: This has to render according to speed limit
    return "50"
}

fun onSpeedLimitChange(speedLimit: Int, locationProvider: FusedLocationProviderClient){
    //TODO: get latitude and longitude

    locationProvider.lastLocation
    //TODO: get time
    //TODO: Check 10 second rule
    //TODO: Register to db

}

fun locationPermission(context: Context){
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        return
    }
}
