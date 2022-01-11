package com.example.trafikkskilt.models

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.trafikkskilt.saveToDb
import com.google.accompanist.permissions.*
import androidx.core.content.ContextCompat.getSystemService


@ExperimentalPermissionsApi
@Composable
fun DrivingView(navController: NavController){
    val context = LocalContext.current
    RequirePermission(){
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
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f),
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
                SaveSpeedSignToDbButton( 70, context)
            }
            4
            Button(
                onClick = {
                    navController.navigate("startView")
                },
                modifier = Modifier
                    .padding(paddingSize * 3)
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

}

fun speedLimit():String{
    //TODO: This has to render according to speed limit
    return "50"
}

fun getLocation(context: Context): Location? {
    val locationManager = getSystemService(context, LocationManager::class.java) as LocationManager
    val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    var location: Location? = null

    if(hasGps || hasNetwork) {
        if (hasGps && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
            val localGpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (localGpsLocation != null) {
                location = localGpsLocation
            }
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                0F
            ) { location = it }
        }
    }
    return location
}

@ExperimentalPermissionsApi
@Composable
fun RequirePermission( content: @Composable() () -> Unit) {
    var doNotShowRationale by rememberSaveable { mutableStateOf(false) }
    //tracks if the user don't want to see the rationale anymore
    val multiplePermissionsState = rememberMultiplePermissionsState(listOf(Manifest.permission.ACCESS_FINE_LOCATION ,Manifest.permission.ACCESS_FINE_LOCATION))

    PermissionsRequired(
        multiplePermissionsState = multiplePermissionsState,
        permissionsNotGrantedContent = {
            if (doNotShowRationale) {
                Text("Feature not available")
            } else {
                Column {
                    Text("The camera is important for this app. Please grant the permission.") //TODO: Fix text
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Button(onClick = { multiplePermissionsState.launchMultiplePermissionRequest() }) {
                            Text("Tillat")
                        }
                        Spacer(Modifier.width(8.dp))
                        Button(onClick = { doNotShowRationale = true }) {
                            Text("Ikke tillat")
                        }
                    }
                }
            }
        },
        permissionsNotAvailableContent = {
            Column {
                Text(
                    "Camera permission denied. See this FAQ with information about why we " +
                            "need this permission. Please, grant us access on the Settings screen."
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }) {
        content()
    }
}

@ExperimentalPermissionsApi
@Composable
fun SaveSpeedSignToDbButton(speedLimit: Int, context: Context){
    Button(onClick = {
        val location = getLocation(context)
        if (location != null)
            saveToDb(speedLimit = speedLimit, latitude = location.latitude, longitude = location.longitude)
    }){
        Text(text = "Save ittt")
    }

    //TODO: Check 10 second rule
}
