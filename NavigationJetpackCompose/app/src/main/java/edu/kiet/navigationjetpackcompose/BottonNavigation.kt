package edu.kiet.navigationjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.kiet.navigationjetpackcompose.ui.theme.NavigationJetpackComposeTheme

class BottonNavigation : ComponentActivity() {
    lateinit var navController:NavHostController
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationJetpackComposeTheme {
                navController= rememberNavController()
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigation{
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination=navBackStackEntry?.destination
                            navList.forEach { item->
                                BottomNavigationItem(
                                    icon={ if (item.badgeCount>0)
                                    {
                                        BadgedBox(badge = { Badge {Text(text = item.badgeCount.toString())}}) {
                                            Icon(item.icon, contentDescription = item.route )
                                        }


                                    } else {
                                        Icon(item.icon, contentDescription = item.route )
                                    }},
                                    label = { Text(text = item.label)},
                                    unselectedContentColor = Color.Yellow,
                                    selected = currentDestination?.hierarchy?.any { it.route==item.route }==true,
                                    onClick = {
                                        navController.navigate(item.route)
                                    })
                            }
                        }
                    }
                ) {
                    NavHost(navController = navController, startDestination = Screen.FirstScreen.route ){
                        composable(Screen.FirstScreen.route){
                            FirstScreen(navController = navController)
                        }
                        composable(Screen.SecondScreen.route,
                            arguments = listOf(navArgument("info"){
                                type= NavType.StringType
                            },
                                navArgument("info2"){
                                    type= NavType.StringType
                                })){
                            val output=it.arguments?.getString("info").toString() + it.arguments?.getString("info2").toString()
                            SecondScreen(navController = navController,output)
                        }
                        composable(Screen.NestedScreen.route){
                            NestedScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

