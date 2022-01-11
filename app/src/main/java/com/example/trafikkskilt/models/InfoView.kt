package com.example.trafikkskilt.models

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trafikkskilt.R
import com.example.trafikkskilt.constants.headerTextSize
import com.example.trafikkskilt.constants.paddingSize
import com.example.trafikkskilt.constants.textSize

/**
 * Info View displays info about the application
 */
@Composable
fun InfoView(navController: NavController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        HeadLine()
        GeneralInfo()
        SetUpList()
        DoneButton(navController = navController)
    }
}

/**
 * Shows the head line
 */
@Composable
fun HeadLine(){
    Text(
        text = stringResource(id = R.string.info_headline),
        modifier = Modifier.padding(start = 0.dp,
                                    top =paddingSize*4,
                                    end = 0.dp,
                                    bottom = paddingSize),
        fontSize = headerTextSize,
        fontWeight = FontWeight.Bold,
    )

    Spacer(modifier = Modifier.padding(paddingSize))
}

/**
 * Shows the general info
 */
@Composable
fun GeneralInfo(){
    Text(
        text = stringResource(id = R.string.info_description_1),
        modifier = Modifier.padding(paddingSize*4),
        fontSize = textSize,
    )

    Spacer(modifier = Modifier.padding(paddingSize))
}

/**
 * Displays the list for set up
 */
@Composable
fun SetUpList(){
    //Bullet pont one
    Text(
        text = stringResource(id = R.string.info_description_2),
        modifier = Modifier.padding(paddingSize),
        fontSize = textSize,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Left,
    )
    //Description for bullet point one
    Text(
        text = stringResource(id = R.string.info_description_3),
        modifier = Modifier.padding(paddingSize*4),
        fontSize = textSize,
    )
    //Bullet point two
    Text(
        text = stringResource(id = R.string.info_description_4),
        modifier = Modifier.padding(paddingSize),
        fontSize = textSize,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Left,
        )
}

/**
 * Done button
 */
@Composable
fun DoneButton(navController: NavController){
    Spacer(modifier = Modifier.padding(100.dp))
    Button(
        onClick = {
            navController.navigate("startView")
        },
        modifier = Modifier.padding(paddingSize),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.button_color)),
    ){
        Text(
            text = stringResource(id = R.string.info_button_text),
            fontSize = textSize,
        )
    }
}
