package com.example.myapplication.ui.screens.userform

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import android.app.Activity
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFormTopBar() {
    val context = LocalContext.current
    TopAppBar(
        title = { Text("Регистрация") }, navigationIcon = {
        IconButton(onClick = {
            (context as? Activity)?.finish()
        }) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад"
            )

        }
    }, actions = {
        IconButton(onClick = {}) {
            Icon(Icons.Default.MoreVert, "Меню")
        }
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.Black,
        titleContentColor = Color.White,
        navigationIconContentColor = Color.White,
        actionIconContentColor = Color.White
    )
    )
}



