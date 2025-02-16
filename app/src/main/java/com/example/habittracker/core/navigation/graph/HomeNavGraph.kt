package com.example.habittracker.core.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.habittracker.core.navigation.screens.MainScreens
import com.example.habittracker.features.habits.HabitsScreen
import com.example.habittracker.features.history.HistoryScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainScreens.Home
    ) {
        composable<MainScreens.Home> {
            HabitsScreen()
        }
        composable<MainScreens.History> {
            HistoryScreen()
        }

    }
}
