package com.example.habittracker.core.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habittracker.core.navigation.screens.AppScreens
import com.example.habittracker.features.main.MainScreen

@Composable
fun RootNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.MainScreen,
    ) {
        composable<AppScreens.MainScreen> { MainScreen() }



    }
}