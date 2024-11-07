package com.sameh.testnav.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)

val topLevelRoutes = listOf(
    TopLevelRoute("Home", HomeNav, Icons.Default.Home),
    TopLevelRoute("Profile", ProfileNav, Icons.Default.Person),
    TopLevelRoute("Setting", SettingsNav, Icons.Default.Settings),
)

@SuppressLint("RestrictedApi")
@Composable
fun AppBottomNavigation(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar(
        modifier = Modifier
            .navigationBarsPadding()
            .padding(16.dp)
            .height(75.dp)
            .clip(CircleShape),
        containerColor = Color(0xFF7559AA),
        tonalElevation = 8.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            topLevelRoutes.forEach { topLevelRoute ->
                val selected =
                    currentDestination?.hierarchy?.any {
                        it.hasRoute(
                            topLevelRoute.route.javaClass.name,
                            null
                        )
                    } == true
                Row(
                    modifier = Modifier
                        .selectedItem(selected)
                        .padding(
                            horizontal = 8.dp,
                            vertical = 8.dp
                        )
                        .clickable {
                            navController.navigate(topLevelRoute.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                ) {
                    Icon(
                        topLevelRoute.icon,
                        contentDescription = topLevelRoute.name,
                        tint = if (selected) Color(0xFF7559AA) else Color.White
                    )
                    AnimatedVisibility(visible = selected) {
                        Text(
                            topLevelRoute.name,
                            color = if (selected) Color(0xFF7559AA) else Color.White
                        )
                    }
                }
                /*
                NavigationBarItem(
                    icon = {
                        Icon(
                            topLevelRoute.icon,
                            contentDescription = topLevelRoute.name,
                            tint = if (selected) Color.Black else Color.Gray
                        )
                    },
                    label = {
                        Text(
                            topLevelRoute.name,
                            color = if (selected) Color.Black else Color.Gray
                        )
                    },
                    selected = selected,
                    onClick = {
                        navController.navigate(topLevelRoute.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
                 */
            }
        }
    }
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.selectedItem(boolean: Boolean): Modifier {
    if (boolean) {
        return Modifier
            .background(color = Color.White, shape = CircleShape)
    }
    return Modifier
}