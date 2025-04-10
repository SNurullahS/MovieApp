package com.nurullahsevinckan.movieapp.presentation.login.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    label: String,
    text: String,
    isError: Boolean? = null,
    keyboardOptions: KeyboardOptions? = null,
    onTextChange: (String) -> Unit,
    placeHolder: String? = null,
    isPassword: Boolean = false,
    companionErrorMessage : String? = null,
    companionError : Boolean? = null
) {
    Column(modifier = Modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange, // Pass the updated value back to LoginScreen
            label = { Text(label) },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 8.dp),
            singleLine = true,
            maxLines = 1,
            isError = isError ?: false,
            keyboardOptions = keyboardOptions ?: KeyboardOptions(keyboardType = KeyboardType.Text),
            leadingIcon = {
                Icon(
                    if(label == "Email")Icons.Filled.Person else Icons.Filled.Lock,
                    contentDescription ="User Name Space")
            },
            placeholder = { Text(text = placeHolder ?: "") }
        )

        if(companionError != null && companionError){
            ErrorText( modifier = Modifier.align(Alignment.Start),
                errorMessage = companionErrorMessage ?: "Error")
        }
    }




}