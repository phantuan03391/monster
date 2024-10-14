package com.kyo.monster.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kyo.monster.ui.navigation.Screen
import com.kyo.monster.ui.screen.HomeScreen
import com.kyo.monster.ui.screen.LoginScreen
import com.kyo.monster.ui.screen.SettingsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        launchSingleTop = true
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }
                }, modifier = modifier
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(modifier)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(modifier)
        }
    }
}
