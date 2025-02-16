package com.example.habittracker.core.navigation.screens

import kotlinx.serialization.Serializable

@Serializable
sealed class MainScreens {
    @Serializable
    data object Home : MainScreens()

    @Serializable
    data object History : MainScreens()


}