package com.example.habittracker.features.main


import com.example.habittracker.R
import com.example.habittracker.core.navigation.screens.MainScreens

sealed class BottomNavItem(
    val route: MainScreens,
    val unselectedIcon: Int,
    val selectedIcon: Int,
    val label: Int,
    val isSelected: Boolean
) {
    data object Home :
        BottomNavItem(
            route = MainScreens.Home,
            unselectedIcon = R.drawable.ic_home_gray,
            selectedIcon = R.drawable.ic_home,
            label = R.string.home,
            isSelected = true
        )

    data object History :
        BottomNavItem(
            route = MainScreens.History,
            unselectedIcon = R.drawable.ic_history,
            selectedIcon = R.drawable.ic_selected_history,
            label = R.string.history,
            isSelected = false
        )
}