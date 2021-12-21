package com.example.trafikkskilt.models

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.trafikkskilt.R
import com.example.trafikkskilt.cameraComponents.CameraView
import com.example.trafikkskilt.constants.paddingSize
import com.example.trafikkskilt.constants.textHeadlineSize
import com.example.trafikkskilt.constants.textSize
import com.google.accompanist.permissions.ExperimentalPermissionsApi

//TODO:NavController
@ExperimentalPermissionsApi
@Composable
fun ConfigCameraView(){
    Surface() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.config_camera_headtitle),
                modifier = Modifier.padding(paddingSize),
                fontSize = textHeadlineSize
            )
            Text(
                text = stringResource(id = R.string.config_camera_description),
                modifier = Modifier.padding(paddingSize),
                fontSize = textSize,
            )
        }
        CameraView()
    }
}