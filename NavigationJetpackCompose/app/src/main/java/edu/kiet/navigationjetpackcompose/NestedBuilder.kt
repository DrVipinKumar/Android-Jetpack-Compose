package edu.kiet.navigationjetpackcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation


fun NavGraphBuilder.nestedRun(navController: NavController){
    navigation(startDestination = Screen.NestedScreen.route, route=Screen.NestedScreenRoute.route){
        composable(Screen.NestedScreen.route){
            NestedScreen(navController = navController)
        }

    }
}