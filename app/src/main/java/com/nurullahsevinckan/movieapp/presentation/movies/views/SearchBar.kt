package com.nurullahsevinckan.movieapp.presentation.movies.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
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
    modifier: Modifier,
    hint : String?,
    onSearch: (String) -> Unit = {}
){

    var text by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint != "") }


    Box(modifier = modifier){
        TextField(value = text , onValueChange = {
            text = it
        },
            keyboardActions = KeyboardActions(onDone = {
                 onSearch(text)
            }),
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(Color.White),
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(Color.White, CircleShape)
                .shadow(5.dp, CircleShape)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                },
            placeholder ={ if(isHintDisplayed){
                Text(text = hint ?: "",
                    color = Color.DarkGray,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp))
            } })


    }

}