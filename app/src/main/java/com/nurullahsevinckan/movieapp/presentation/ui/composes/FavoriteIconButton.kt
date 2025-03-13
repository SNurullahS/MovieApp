package com.nurullahsevinckan.movieapp.presentation.ui.composes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nurullahsevinckan.movieapp.util.ScreenUtil

@Composable
fun FavoriteIconButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val screenWidth = ScreenUtil.getScreenWidth()
    val iconSize = if (screenWidth > 600.dp) 48.dp else 32.dp // For dynamic size for icon

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = "Toggle Favorite",
                tint = if (isFavorite) Color.Red else Color.White,
                modifier = Modifier.size(iconSize)
            )
        }
    }
}
