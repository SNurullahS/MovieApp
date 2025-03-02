package com.nurullahsevinckan.movieapp.presentation.login.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nurullahsevinckan.movieapp.presentation.Screen
import com.nurullahsevinckan.movieapp.presentation.login.LoginEvents
import com.nurullahsevinckan.movieapp.presentation.login.LoginViewModel
import com.nurullahsevinckan.movieapp.presentation.ui.composes.CustomButton

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isUserLoggedIn by loginViewModel.isUserLoggedIn

    LaunchedEffect(isUserLoggedIn) {
        if (isUserLoggedIn) {
            navController.navigate(Screen.MovieScreen.route) {
                popUpTo(Screen.LoginScreen.route) { inclusive = true }
            }
        }
    }

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

        // Pass the state and state updater function
        CustomTextField(label = "Email", text = email, onTextChange = { email = it })
        CustomTextField(label = "Password", text = password, isPassword = true, onTextChange = { password = it })

        CustomButton(text = "Login") {
            loginViewModel.onEvent(LoginEvents.Login(email,password))
            println("login buttonuna basıldı")
        }

        CustomButton(text = "Sign Up") {
            loginViewModel.onEvent(LoginEvents.SignIn(email,password))
            println("sign up buttonuna basıldı")
        }
    }
}
