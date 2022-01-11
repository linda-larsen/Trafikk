package com.example.trafikkskilt.models

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trafikkskilt.R
import com.example.trafikkskilt.cameraComponents.CameraView
import com.example.trafikkskilt.constants.paddingSize
import com.example.trafikkskilt.constants.textHeadlineSize
import com.example.trafikkskilt.constants.textSize
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * View for configuration of camera
 *
 * @param navController NavController
 */

@ExperimentalCoroutinesApi
@ExperimentalPermissionsApi
@Composable
fun ConfigCameraView(navController: NavController){
    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.config_camera_headtitle),
                modifier = Modifier.padding(paddingSize),
                fontSize = textHeadlineSize,
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(id = R.string.config_camera_description),
                modifier = Modifier.padding(paddingSize),
                fontSize = textSize,
                textAlign = TextAlign.Center,
            )
            Row{
                Button(
                    onClick = {
                        navController.navigate("startView")
                    },
                    modifier = Modifier.padding(paddingSize).fillMaxWidth().weight(1f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.button_grey)),
                ) {
                    Text(text = stringResource(id = R.string.cancel_camera_config))
                }
                Button(
                    onClick = {
                        navController.navigate("drivingView")
                    },
                    modifier = Modifier.padding(paddingSize)
                        .fillMaxWidth().weight(1f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.button_color)),
                ) {
                    Text(text = stringResource(id = R.string.config_camera_done_button))
                }
            }
            CameraView(modifier = Modifier.padding(paddingSize).fillMaxHeight(3f))
        }
    }
}
