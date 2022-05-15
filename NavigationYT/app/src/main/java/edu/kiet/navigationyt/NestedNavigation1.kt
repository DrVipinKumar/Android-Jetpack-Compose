package edu.kiet.navigationyt

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.addNavigationOne(navController: NavController){
    navigation(startDestination = Screen.NestedScreenOne.route,route=Screen.NavigationRoute.route){
        composable(Screen.NestedScreenOne.route){
            NestedScreenOne(navController)
        }
        composable(Screen.NestedScreenTwo.route){
            NestedScreenTwo(navController)
        }
    }
}