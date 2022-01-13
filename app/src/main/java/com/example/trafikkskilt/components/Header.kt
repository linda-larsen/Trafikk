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
import com.example.trafikkskilt.constants.paddingSize
import com.example.trafikkskilt.constants.headerHeight
import com.example.trafikkskilt.constants.headerTextSize
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.Menu
import androidx.navigation.NavController
import com.example.trafikkskilt.constants.menuSize

/**
 * The Header component
 * @param navController
 */
@ExperimentalMaterialApi
@Composable
fun HeaderComponent(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf(
        stringResource(id = R.string.header_line_one),
        stringResource(id = R.string.header_line_two),
        stringResource(id = R.string.header_line_three))


    Surface(
        color = colorResource(id = R.color.dark_red).copy(alpha = 0.5f),//Color.Red.copy(alpha = 0.6f),
        modifier = Modifier
            .fillMaxSize()
            .height(headerHeight),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // The logo, viewed to the left
            Logo(navController = navController,
                modifier = Modifier
                    .size(48.dp)
                    .weight(1f)
                    .padding(paddingSize * 2)
            )

            //The title, centered
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .padding(paddingSize*2)
                    .fillMaxSize()
                    .weight(5f),
                fontFamily = FontFamily.Serif,
                fontSize = headerTextSize,
            )

            //The hamburger menu with dropdown menu
            HamburgerDropDownMenu(
                menuItems = items,
                menuExpandedState = expanded,
                updateMenuExpandStatus = {
                    expanded = true
                },
                onDismissMenuView = {
                    expanded = false
                },
                onMenuItemClick = {
                    expanded = false
                },
                navController
            )
        }
    }
}

/**
 * The logo
 * @param navController
 * @param modifier
 */

@ExperimentalMaterialApi
@Composable
fun Logo(navController: NavController, modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = CircleShape,
        elevation = 2.dp,
        onClick = {
            navController.navigate("startView")
        }
    ) {
        Image(
            painterResource(R.drawable.logo),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

/**
 * The hamburger menu button
 * @param updateMenuExpandStatus as Unit
 */
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

/**
 * The hamburger drop down menu
 * @param menuItems as List of strings
 * @param menuExpandedState as boolean
 * @param updateMenuExpandStatus as Unit
 * @param onDismissMenuView as Unit
 * @param onMenuItemClick as Unit
 * @param navController as NavController
 */
@Composable
fun HamburgerDropDownMenu(
    menuItems: List<String>,
    menuExpandedState: Boolean,
    updateMenuExpandStatus : () -> Unit,
    onDismissMenuView : () -> Unit,
    onMenuItemClick : (Int) -> Unit,
    navController: NavController
) {
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
                    if (index == 0) {
                        navController.navigate("startView")
                    } else if (index == 1) {
                        navController.navigate("calibrateView")
                    } else {
                        navController.navigate("infoView")
                    }
                }) {
                Text(text = title)
            }
        }
    }
}
