package com.walid.news.ui.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.walid.news.ui.components.SplashScreen
import com.walid.news.ui.screens.HomeScreen


@Composable


fun AppNavigationGraph(){

    val navController= rememberNavController()
    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("homeScreen") { HomeScreen() }
    }



}