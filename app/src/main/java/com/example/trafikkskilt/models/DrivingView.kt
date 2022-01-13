package com.example.trafikkskilt.models

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
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

/**
 * The view that is shown while driving
 *
 * @param navController NavController
 */

@ExperimentalPermissionsApi
@Composable
fun DrivingView(navController: NavController){
    val context = LocalContext.current
    RequirePermission{
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
                    fontSize = 90.sp,
                    modifier = Modifier.padding(0.dp, 35.dp, 0.dp, 0.dp)

                )
            }
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
            SaveSpeedSignToDbButton(convertSpeedLimitToInt(speedLimit()), context)//TODO: Remove

        }
    }

}

/**
 * Method for registration of speedlimit
 */
fun speedLimit(): String{
    //TODO: This has to render according to speed limit
    return  "110"
}

/**
 * Converts speed limit from string to int
 * @param speedLimit as String
 * @return speed limit as in
 */
fun convertSpeedLimitToInt(speedLimit: String): Int{
    try {
        return speedLimit.toInt()
    } catch (nfe: NumberFormatException) {
        Log.e("Speed limit not casted", "Failed to cast speed limit to int")
    }
    return 0
}

/**
 * Gets the location of the phone
 * @param context the context
 * @return the location
 */
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

/**
 * Requiers permission to acsess location data if nedded
 */
@ExperimentalPermissionsApi
@Composable
fun RequirePermission( content: @Composable () -> Unit) {
    var doNotShowRationale by rememberSaveable { mutableStateOf(false) }
    //tracks if the user don't want to see the rationale anymore
    val multiplePermissionsState = rememberMultiplePermissionsState(listOf(Manifest.permission.ACCESS_FINE_LOCATION ,Manifest.permission.ACCESS_FINE_LOCATION))

    PermissionsRequired(
        multiplePermissionsState = multiplePermissionsState,
        permissionsNotGrantedContent = {
            if (doNotShowRationale) {
                Text(stringResource(id = R.string.location_permission))
            } else {
                Column {
                    Text(stringResource(id = R.string.location_permission))
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Button(onClick = { multiplePermissionsState.launchMultiplePermissionRequest() }) {
                            Text(stringResource(id = R.string.allow))
                        }
                        Spacer(Modifier.width(8.dp))
                        Button(onClick = { doNotShowRationale = true }) {
                            Text(stringResource(id = R.string.dont_allow))
                        }
                    }
                }
            }
        },
        permissionsNotAvailableContent = {
            Column {
                Text(
                    stringResource(id = R.string.location_permission_missing))
                Spacer(modifier = Modifier.height(8.dp))
            }
        }) {
        content()
    }
}

/**
 * Saves speed sign to database
 * @param speedLimit as Int
 * @param context as Context
 */
@ExperimentalPermissionsApi
@Composable
fun SaveSpeedSignToDbButton(speedLimit: Int, context: Context){
    Button(onClick = {
        val location = getLocation(context)
        if (location != null)
            saveToDb(speedLimit = speedLimit, latitude = location.latitude, longitude = location.longitude)
        },
        modifier = Modifier.padding(paddingSize),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.button_color)),
    ){
        Text(text = "Save it")
    }
}
