package com.example.habittracker.features.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.habittracker.core.navigation.graph.HomeNavGraph
import com.example.habittracker.core.theme.Grey300
import com.example.habittracker.core.theme.PrimaryColor
import com.example.habittracker.core.theme.RedColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val bottomNavController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(bottomNavController) }
    ) { paddingValue ->
        HomeNavGraph(bottomNavController)

    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.History
    )

    var navigationSelectedItem by remember { mutableIntStateOf(0) }
    NavigationBar(
        containerColor = White,
        modifier = Modifier.fillMaxWidth()
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(

                colors = NavigationBarItemColors(
                    selectedIconColor = PrimaryColor,
                    selectedTextColor = RedColor,
                    selectedIndicatorColor = White,
                    unselectedIconColor = Grey300,
                    unselectedTextColor = Grey300,
                    disabledIconColor = Grey300,
                    disabledTextColor = Grey300,
                ),
                icon = {
                    Icon(
                        painter =
                        if (index == navigationSelectedItem) {
                            painterResource(item.selectedIcon)
                        } else {
                            painterResource(item.unselectedIcon)
                        },
                        contentDescription = stringResource(item.label)
                    )
                },
                label = {
                    Text(text = stringResource(item.label))
                },
                selected = index == navigationSelectedItem,
                onClick = {
                    navigationSelectedItem = index
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


