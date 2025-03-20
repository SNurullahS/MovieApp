package com.nurullahsevinckan.movieapp.presentation.navigation.views

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.nurullahsevinckan.movieapp.presentation.navigation.BottomNavViewModel

@Composable
fun BottomNavBar(
    navController: NavController,
    viewModel: BottomNavViewModel = hiltViewModel()
) {
    val items by viewModel.bottomNavItems.collectAsState()

    var selectedItemIndex by remember {
        mutableIntStateOf(0)
    }


    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    if (selectedItemIndex != index) selectedItemIndex = index
                    /*
                    if (navController.currentDestination?.route != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                     */
                },
                label = { Text(item.title) },
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount != null) {
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            } else if (item.hasNews == true) {
                                Badge()
                            }

                        }
                    ) {
                        Icon(
                            imageVector =
                            if (index == selectedItemIndex) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            //if (navController.currentDestination?.route == item.route) {
                            //    item.selectedIcon
                            //} else {
                            //    item.unselectedIcon
                            //}
                            contentDescription = item.title
                        )
                    }
                }
            )
        }
    }
}