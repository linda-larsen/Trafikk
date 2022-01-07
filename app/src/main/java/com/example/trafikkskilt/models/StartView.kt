package com.example.trafikkskilt.models

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trafikkskilt.R


    @Composable
    fun StartView(navController: NavController) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            elevation = 2.dp,
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.welcome),
                    Modifier
                        .padding(30.dp)
                        .fillMaxHeight()
                        .weight(1f),
                    fontSize = 30.sp,
                    
                )
                Button(
                    onClick = { navController.navigate("drivingView") },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.button_color)),
                    modifier = Modifier
                        .padding(30.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(1.5f),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.car3),
                            contentDescription = stringResource(id = R.string.app_intro_image_content_description),
                            tint = colorResource(id = R.color.black),
                            modifier = Modifier.size(100.dp)

                        )
                        Text(
                            text = stringResource(R.string.start_drive_button),
                            modifier = Modifier.padding(4.dp),
                            fontSize = 30.sp,
                            color = colorResource(id = R.color.black)
                        )
                    }
                }
                Spacer(modifier = Modifier
                    .height(20.dp)
                    .fillMaxHeight()
                    .weight(1f))
            }
        }
    }
