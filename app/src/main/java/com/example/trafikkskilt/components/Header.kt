package com.example.trafikkskilt.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.trafikkskilt.R
import com.example.trafikkskilt.constants.PaddingSize
import com.example.trafikkskilt.constants.headerHeigth
import com.example.trafikkskilt.constants.headerTextSize
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.Menu
import androidx.navigation.NavController
import com.example.trafikkskilt.constants.menuSize

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
            // The logo, viewed to the left
            Logo()

            //The title, centered
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.padding(PaddingSize*2),
                fontFamily = FontFamily.Serif,
                fontSize = headerTextSize,
            )

            //The hamburger menu with dropdown menu
            HamburgerDropDownMenu(
                menuItems = items,
                menuExpandedState = expanded,
                selectedIndex = selectedIndex,
                updateMenuExpandStatus = {
                    expanded = true
                },
                onDismissMenuView = {
                    expanded = false
                },
                onMenuItemClick = { index->
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

@Composable
fun HamburgerDropDownMenu(
    menuItems: List<String>,
    menuExpandedState: Boolean,
    selectedIndex : Int,
    updateMenuExpandStatus : () -> Unit,
    onDismissMenuView : () -> Unit,
    onMenuItemClick : (Int) -> Unit,
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
                    onMenuItemClick(index)
                    if(selectedIndex == 0){
                        navController.navigate("startView")
                    } else {
                        navController.navigate("test") //TODO: Route to calibrateView her
                       // navController.navigate("calibrate")
                    }
                }) {
                Text(text = title)
            }
        }
    }
}
