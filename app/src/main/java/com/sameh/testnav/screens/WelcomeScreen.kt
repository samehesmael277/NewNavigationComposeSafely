package com.sameh.testnav.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.sameh.testnav.LocalNavigationProvider
import com.sameh.testnav.navigation.Login

@Composable
fun WelcomeScreen(

) {
    val navController = LocalNavigationProvider.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome",
            fontSize = 20.sp,
            color = Color.Black
        )
        Button(
            onClick = {
                navController.navigate(Login) {
                    popUpTo(0)
                }
            }
        ) {
            Text(text = "Nav to Login")
        }
    }
}