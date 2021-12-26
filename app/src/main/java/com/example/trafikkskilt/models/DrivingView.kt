package com.example.trafikkskilt.models

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trafikkskilt.R

@Composable
fun DrivingView(navController: NavController){
    Column() {
        Text(
            text = stringResource(id = R.string.speedlimit_is),
            Modifier
                .padding(30.dp)
                .fillMaxHeight()
                .weight(1f),
            fontSize = 30.sp,
        )



        Box {
            Icon(
                painter = painterResource(R.drawable.sign),
                contentDescription = stringResource(id = R.string.app_intro_image_content_description),
                tint = colorResource(id = R.color.black),
                modifier = Modifier
                    .size(100.dp)
            )
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Hello")
            }
        }
    }
}