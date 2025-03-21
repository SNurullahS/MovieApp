package com.nurullahsevinckan.movieapp.presentation.login.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
@Composable
fun CustomTextField(label: String, text: String, onTextChange: (String) -> Unit, isPassword: Boolean = false) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange, // Pass the updated value back to LoginScreen
        label = { Text(label) },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp, vertical = 8.dp),
        singleLine = true,
        maxLines = 1
    )
}