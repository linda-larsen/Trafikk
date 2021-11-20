package com.example.trafikkskilt.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.trafikkskilt.R
import com.example.trafikkskilt.constants.PaddingSize
import com.example.trafikkskilt.constants.headerHeigth
import com.example.trafikkskilt.constants.headerTextSize
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.Menu
import com.example.trafikkskilt.constants.menuSize


class Header:ViewModel() {
//TODO: Style text

}

@Composable
fun HeaderComponent() {
    Surface(
        color = colorResource(id = R.color.white).copy(alpha = 0.8f),//Color.Red.copy(alpha = 0.6f),
        modifier = Modifier.fillMaxSize().height(headerHeigth),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Logo()
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.padding(PaddingSize*2),
                fontFamily = FontFamily.Serif, //TODO: Make font Roboto
                fontSize = headerTextSize,

            )
            Menu()
        }
    }
}

@Composable
fun Logo() {
    Card(
        modifier = Modifier.size(48.dp).padding(PaddingSize*2),
        shape = CircleShape,
        elevation = 2.dp,
    ) {
        Image(
            painterResource(R.drawable.logo),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun Menu(){
    //var showMenu by remember { mutableStateOf(false) }
    IconButton(
        onClick = {
            //TODO
        },
        modifier = Modifier.size(menuSize)
    ) {
        Icon(Icons.Filled.Menu,"")
    }
}