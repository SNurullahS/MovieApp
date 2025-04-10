package com.nurullahsevinckan.movieapp.presentation.login.views

import android.app.ProgressDialog.show
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.excludeFromSystemGesture
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nurullahsevinckan.movieapp.presentation.Screen
import com.nurullahsevinckan.movieapp.presentation.login.LoginEvents
import com.nurullahsevinckan.movieapp.presentation.login.LoginViewModel
import com.nurullahsevinckan.movieapp.presentation.login.RegistrationFromState
import com.nurullahsevinckan.movieapp.presentation.login.ValidationEvent
import com.nurullahsevinckan.movieapp.presentation.ui.composes.CustomButton
import kotlin.math.log

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoginUiVisible by remember { mutableStateOf(true) }
    val isUserLoggedIn by loginViewModel.isUserLoggedIn
    val toastMessage by loginViewModel.toastMessage
    val context = LocalContext.current
    val validation = loginViewModel.validationState


    LaunchedEffect(key1 = context) {
        loginViewModel.validationEvent.collect { event ->
            when (event) {
                ValidationEvent.Successful -> {
                    Toast.makeText(
                        context,
                        "Registration successful",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        }
    }


    LaunchedEffect(isUserLoggedIn) {
        if (isUserLoggedIn) {
            navController.navigate(Screen.MovieScreen.route) {
                popUpTo(Screen.LoginScreen.route) { inclusive = true }
            }
        }
    }

    LaunchedEffect(toastMessage) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            loginViewModel.clearToastMessage()
        }
    }

    LaunchedEffect(validation) {
        if (validation.passwordError?.isNotEmpty() == true || validation.emailError?.isNotEmpty() == true || validation.repeatPasswordError?.isNotEmpty() == true) {
            isLoginUiVisible = false
        } else {
            isLoginUiVisible = true
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .imePadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        if (isLoginUiVisible) {
            Image(
                painter = painterResource(id = android.R.drawable.ic_lock_idle_lock),
                contentDescription = "Login Icon",
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 16.dp)
            )


            Text(
                text = "Login",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Pass the state and state updater function
        CustomTextField(
            label = "Email",
            text = email,
            onTextChange = {
                loginViewModel.onEvent(LoginEvents.EmailChecked(it))
                email = it
            },
            isError = validation.emailError != null,
            placeHolder = "Email",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            companionError = validation.emailError != null,
            companionErrorMessage = validation.emailError
        )

        CustomTextField(
            label = "Password",
            text = password,
            isPassword = true,
            onTextChange = {
                loginViewModel.onEvent(LoginEvents.PasswordChanged(it))
                password = it
            },
            isError = validation.passwordError != null,
            placeHolder = "password",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            companionError = validation.passwordError != null,
            companionErrorMessage = validation.passwordError
        )


        CustomTextField(
            label = "RepeatedPassword",
            text = validation.repeatPassword,
            isPassword = true,
            onTextChange = {
                loginViewModel.onEvent(LoginEvents.RepeatedPasswordChanged(it))
            },
            isError = validation.repeatPasswordError != null,
            placeHolder = "Repeat Password",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            companionError = validation.repeatPasswordError != null,
            companionErrorMessage = validation.repeatPasswordError
        )

        CustomButton(text = "Login") {
            loginViewModel.onEvent(LoginEvents.Login(email, password))
            println("login buttonuna basıldı")
        }

        CustomButton(text = "Sign Up") {
            loginViewModel.onEvent(LoginEvents.SignIn(email, password))
            println("sign up buttonuna basıldı")
        }
    }
}
