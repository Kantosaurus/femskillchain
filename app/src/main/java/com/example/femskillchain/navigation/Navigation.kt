package com.example.femskillchain.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.femskillchain.screens.LoginScreen
import com.example.femskillchain.screens.SignUpScreen
import com.example.femskillchain.screens.ForgotPasswordScreen
import com.example.femskillchain.screens.AccountSettingsScreen
import com.example.femskillchain.screens.HomeScreen
import com.example.femskillchain.screens.BottomNavItem
import com.example.femskillchain.screens.AssessmentScreen
import com.example.femskillchain.screens.AssessmentAnalysisScreen
import com.example.femskillchain.screens.MarketplaceScreen
import com.example.femskillchain.screens.JobDetailScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object ForgotPassword : Screen("forgot_password")
    object AccountSettings : Screen("account_settings")
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
    object Assessment : Screen("assessment")
    object AssessmentAnalysis : Screen("assessment_analysis")
    object Marketplace : Screen("marketplace")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(
            route = Screen.Login.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            }
        ) {
            LoginScreen(navController)
        }
        
        composable(
            route = Screen.SignUp.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            }
        ) {
            SignUpScreen(navController)
        }
        
        composable(
            route = Screen.ForgotPassword.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(300)
                )
            }
        ) {
            ForgotPasswordScreen(navController)
        }
        
        composable(
            route = Screen.AccountSettings.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            }
        ) {
            AccountSettingsScreen(navController)
        }

        // Bottom Navigation Routes
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        
        composable(route = Screen.Explore.route) {
            HomeScreen(navController)
        }
        
        composable(route = Screen.Profile.route) {
            HomeScreen(navController)
        }
        
        composable(route = Screen.Settings.route) {
            HomeScreen(navController)
        }

        composable(route = Screen.Marketplace.route) {
            HomeScreen(navController)
        }

        // Assessment Routes
        composable(Screen.Assessment.route) {
            AssessmentScreen(navController)
        }

        composable(
            route = Screen.AssessmentAnalysis.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            }
        ) {
            AssessmentAnalysisScreen(navController = navController)
        }

        composable(
            route = "jobDetail/{jobTitle}",
            arguments = listOf(navArgument("jobTitle") { type = NavType.StringType })
        ) { backStackEntry ->
            val jobTitle = backStackEntry.arguments?.getString("jobTitle") ?: ""
            JobDetailScreen(jobTitle)
        }
    }
} 