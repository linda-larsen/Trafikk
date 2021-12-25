package com.example.trafikkskilt.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.navigation.NavController
import com.example.trafikkskilt.constants.menuSize


class Header:ViewModel() {
//TODO: Style text

}

@Composable
fun HeaderComponent(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Start drive", "Calibrate camera")
    var selectedIndex by remember { mutableStateOf(0) }


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
            //TODO: Clean up code here
            HamburgerDropDownMenu(
                items,
                expanded,
                selectedIndex,
                updateMenuExpandStatus = {
                    expanded = true
                },
                onDismissMenuView = {
                    expanded = false
                },
                onMenuItemclick = { index->
                    selectedIndex = index
                    expanded = false
                },
                navController
            )
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
fun HamburgerMenuButton(updateMenuExpandStatus : () -> Unit){
    IconButton(
        onClick = {
            updateMenuExpandStatus()        },
        modifier = Modifier.size(menuSize)
    ) {
        Icon(Icons.Filled.Menu,"")
    }
}


//TODO: Make clicks on menu items take you the desired route
@Composable
fun HamburgerDropDownMenu(
    menuItems: List<String>,
    menuExpandedState: Boolean,
    seletedIndex : Int,
    updateMenuExpandStatus : () -> Unit,
    onDismissMenuView : () -> Unit,
    onMenuItemclick : (Int) -> Unit,
    navController: NavController
){
    //The hamburger menu
    HamburgerMenuButton(updateMenuExpandStatus)

    //The dropdown menu that opens when you click the hamburger menu icon
    DropdownMenu(
        expanded = menuExpandedState,
        onDismissRequest = { onDismissMenuView() },
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
    ) {
        menuItems.forEachIndexed { index, title ->
            DropdownMenuItem(
                onClick = {
                    onMenuItemclick(index)
                    if(seletedIndex == 0){
                        navController.navigate("test")
                    }
                }) {
                Text(text = title)
            }
        }
    }
}
