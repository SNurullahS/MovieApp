package com.nurullahsevinckan.movieapp.presentation.ui.composes

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Composable
fun MovieSearchBar(
    modifier: Modifier = Modifier,
    hint: String? = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint != "") }

    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray
            )
        },
        placeholder = {
            if (isHintDisplayed) {
                Text(
                    text = hint ?: "",
                    color = Color.Gray
                )
            }
        },
        singleLine = true,
        maxLines = 1,
        textStyle = TextStyle(color = Color.Black),
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.colors(
            Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardActions = KeyboardActions(onDone = { onSearch(text) }),
        modifier = modifier
            .height(48.dp)
            .onFocusChanged {
                isHintDisplayed = it.isFocused != true && text.isEmpty()
            }
    )
}
