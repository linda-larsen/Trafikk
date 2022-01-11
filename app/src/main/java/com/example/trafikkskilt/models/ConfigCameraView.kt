package com.example.trafikkskilt.models

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.trafikkskilt.R
import com.example.trafikkskilt.cameraComponents.CameraView
import com.example.trafikkskilt.constants.paddingSize
import com.example.trafikkskilt.constants.textHeadlineSize
import com.example.trafikkskilt.constants.textSize
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi


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
                text = stringResource(id = R.string.camera_permission),
                modifier = Modifier.padding(paddingSize),
                fontSize = textSize,
                textAlign = TextAlign.Center,
            )
            Button(
                onClick = {
                    navController.navigate("startView")
                },
            ) {
                Text(text = stringResource(id = R.string.config_camera_done_button))
            }
            CameraView(modifier = Modifier.padding(paddingSize).fillMaxHeight(3f))

        }
    }
}
