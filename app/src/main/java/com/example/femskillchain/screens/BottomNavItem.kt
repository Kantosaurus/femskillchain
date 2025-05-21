package com.example.femskillchain.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.femskillchain.navigation.Screen

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = Screen.Home.route,
        title = "Home",
        icon = Icons.Default.Home
    )
    object Explore : BottomNavItem(
        route = Screen.Explore.route,
        title = "Explore",
        icon = Icons.Default.Explore
    )
    object Assessment : BottomNavItem(
        route = Screen.Assessment.route,
        title = "Assessment",
        icon = Icons.Default.Psychology
    )
    object Marketplace : BottomNavItem(
        route = Screen.Marketplace.route,
        title = "Marketplace",
        icon = Icons.Default.Work
    )
    object Profile : BottomNavItem(
        route = Screen.Profile.route,
        title = "Profile",
        icon = Icons.Default.Person
    )
}

@Composable
fun BottomNavItem(item: BottomNavItem) {
    Icon(
        imageVector = item.icon,
        contentDescription = item.title
    )
    Text(text = item.title)
} 