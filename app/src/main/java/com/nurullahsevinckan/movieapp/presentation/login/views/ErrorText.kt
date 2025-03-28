package com.nurullahsevinckan.movieapp.presentation.login.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorText(
    modifier: Modifier = Modifier,
    errorMessage: String,
    errorColor: Color? = null,
    spacerDp : Dp? = null
) {
    Text(modifier = Modifier,
        text = errorMessage,
        color = errorColor ?: MaterialTheme.colorScheme.error,
        fontSize = 12.sp)

    Spacer(modifier = Modifier.height(spacerDp ?: 16.dp))
}