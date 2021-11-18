package com.example.trafikkskilt.models

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.trafikkskilt.R
import java.lang.reflect.Modifier

class StartView : ViewModel() {

    @Composable
    fun startView() {
        Surface(
            shape = MaterialTheme.shapes.medium,
            elevation = 2.dp,
        ){
            Column() {
                Text("Henlo")
            }
        }
    }

}