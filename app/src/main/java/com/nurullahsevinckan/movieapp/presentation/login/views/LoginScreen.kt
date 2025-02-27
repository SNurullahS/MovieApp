package com.nurullahsevinckan.movieapp.presentation.login.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_lock_idle_lock),
            contentDescription = "Login Icon",
            modifier = Modifier.size(100.dp).padding(bottom = 16.dp)
        )

        Text(
            text = "Login",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        CustomTextField(label = "Email")
        CustomTextField(label = "Password", isPassword = true)

        CustomButton(text = "Login") {
            // Login
        }

        CustomButton(text = "Sign Up") {
            // Sign Up
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}