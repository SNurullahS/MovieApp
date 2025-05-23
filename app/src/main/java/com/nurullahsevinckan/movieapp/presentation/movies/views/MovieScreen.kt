package com.nurullahsevinckan.movieapp.presentation.movies.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nurullahsevinckan.movieapp.presentation.Screen
import com.nurullahsevinckan.movieapp.presentation.movies.MoviesEvent
import com.nurullahsevinckan.movieapp.presentation.movies.MoviesViewModel
import com.nurullahsevinckan.movieapp.presentation.ui.composes.MovieSearchBar
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.nurullahsevinckan.movieapp.presentation.ui.composes.OverflowMenu
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import com.nurullahsevinckan.movieapp.presentation.ui.composes.MovieScreenUpButton
import com.nurullahsevinckan.movieapp.presentation.navigation.BottomNavViewModel

@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel(),
    bottomNavViewModel: BottomNavViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val isLogout by viewModel.isUserLoggedOut
    val listState = rememberLazyListState()
    
    // Get the search hint and content type from BottomNavViewModel
    val searchHint by bottomNavViewModel.searchHint.collectAsState()
    val contentType by bottomNavViewModel.selectedContentType.collectAsState()
    
    // Debug logs to track changes
    LaunchedEffect(contentType) {
        Log.d("MovieScreen", "Content type changed to: $contentType")
        viewModel.setContentType(contentType)
    }
    
    LaunchedEffect(searchHint) {
        Log.d("MovieScreen", "Search hint changed to: $searchHint")
    }

    // Show scroll to top button only when not at the top
    val showScrollToTop by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    val context = LocalContext.current
    val emptySearchStringMessage = "Search space can not be empty!"
    val visibleItemInfo = listState.layoutInfo.visibleItemsInfo

    // Check if we need to load more movies
    LaunchedEffect(visibleItemInfo, state.movies.size) {
        val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
        if (lastVisibleItem != null) {
            val lastIndex = lastVisibleItem.index
            val totalItems = state.movies.size
            
            // If we're close to the end of the list and not already loading more
            if (lastIndex >= totalItems - 3 && !state.isLoading && !state.isLoadingMore && !state.endReached) {
                viewModel.onEvent(MoviesEvent.LoadMore)
            }
        }
    }

    LaunchedEffect(isLogout) {
        if (isLogout) {
            navController.navigate(Screen.LoginScreen.route) {
                popUpTo(Screen.MovieScreen.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            // for avoid camera space
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Search bar
                MovieSearchBar(
                    modifier = Modifier
                        .weight(1f),
                    hint = searchHint,
                    onSearch = {
                        if (it.isNotEmpty()){viewModel.onEvent(MoviesEvent.Search(it))}
                        else{Toast.makeText(context, emptySearchStringMessage, Toast.LENGTH_SHORT).show()}
                    }
                )

                //logout nad fav list
                OverflowMenu(
                    navController,
                    favList = {
                        println("Go to fav list of movies")
                    },
                    logout = {
                        println("Logged out")
                        viewModel.logoutExecute()
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = listState
                ) {
                    items(state.movies) { movie ->
                        MovieListRow(
                            movie = movie,
                            onItemClick = {
                                navController.navigate(
                                    Screen.MovieDetailScreen.route + "/${movie.imdbID}"
                                )
                            }
                        )
                    }
                    
                    item {
                        if (state.isLoadingMore) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = Color.White)
                            }
                        }
                    }
                }
                
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White
                    )
                }
                
                if (state.error.isNotEmpty() && state.movies.isEmpty()) {
                    Text(
                        text = state.error,
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
                
                // Scroll to top FAB
                if (showScrollToTop) {
                    MovieScreenUpButton(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp),
                        listState = listState
                    )
                }
            }
        }
    }
}
