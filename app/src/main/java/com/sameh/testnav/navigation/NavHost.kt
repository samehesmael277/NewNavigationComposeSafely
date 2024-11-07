package com.sameh.testnav.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sameh.testnav.LocalNavigationProvider
import com.sameh.testnav.screens.EditProfileScreen
import com.sameh.testnav.screens.FromHomeScreen
import com.sameh.testnav.screens.HomeScreen
import com.sameh.testnav.screens.LoginScreen
import com.sameh.testnav.screens.ProfileScreen
import com.sameh.testnav.screens.SettingsItemScreen
import com.sameh.testnav.screens.SettingsScreen
import com.sameh.testnav.screens.WelcomeScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavigationProvider provides navController) {
        NavHost(
            navController = navController,
            startDestination = Welcome,
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable<Welcome> {
                WelcomeScreen()
            }
            composable<Login> {
                LoginScreen()
            }
            composable<AppNav> {
                val appNavNavController = rememberNavController()
                CompositionLocalProvider(LocalNavigationProvider provides appNavNavController) {
                    Scaffold(
                        bottomBar = {
                            AppBottomNavigation(appNavNavController)
                        },
                        containerColor = Color.White
                    ) {
                        NavHost(
                            navController = appNavNavController,
                            startDestination = HomeNav,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                        ) {
                            composable<HomeNav> {
                                val homeNavNavController = rememberNavController()
                                CompositionLocalProvider(LocalNavigationProvider provides homeNavNavController) {
                                    NavHost(
                                        navController = homeNavNavController,
                                        startDestination = Home,
                                        modifier = Modifier
                                            .fillMaxSize()
                                    ) {
                                        composable<Home> {
                                            HomeScreen()
                                        }
                                        composable<FromHome> { backStackEntry ->
                                            val fromHome = backStackEntry.toRoute<FromHome>()
                                            FromHomeScreen(fromHome.id)
                                        }
                                    }
                                }
                            }
                            composable<ProfileNav> {
                                val profileNavNavController = rememberNavController()
                                CompositionLocalProvider(LocalNavigationProvider provides profileNavNavController) {
                                    NavHost(
                                        navController = profileNavNavController,
                                        startDestination = Profile,
                                        modifier = Modifier
                                            .fillMaxSize()
                                    ) {
                                        composable<Profile> {
                                            ProfileScreen()
                                        }
                                        composable<EditProfile> { backStackEntry ->
                                            val editProfile = backStackEntry.toRoute<EditProfile>()
                                            EditProfileScreen(editProfile.image)
                                        }
                                    }
                                }
                            }
                            composable<SettingsNav> {
                                val settingsNavNavController = rememberNavController()
                                CompositionLocalProvider(LocalNavigationProvider provides settingsNavNavController) {
                                    NavHost(
                                        navController = settingsNavNavController,
                                        startDestination = Settings,
                                        modifier = Modifier
                                            .fillMaxSize()
                                    ) {
                                        composable<Settings> {
                                            SettingsScreen()
                                        }
                                        composable<SettingsItem> { backStackEntry ->
                                            val settingsItem =
                                                backStackEntry.toRoute<SettingsItem>()
                                            SettingsItemScreen(settingsItem.itemId)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}