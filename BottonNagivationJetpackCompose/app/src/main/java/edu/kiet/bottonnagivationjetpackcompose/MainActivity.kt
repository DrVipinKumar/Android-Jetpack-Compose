package edu.kiet.bottonnagivationjetpackcompose

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
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.kiet.bottonnagivationjetpackcompose.ui.theme.BottonNagivationJetpackComposeTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottonNagivationJetpackComposeTheme {
                navController= rememberNavController()
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                            BottomNavigation {
                                val navBackStackStrace by navController.currentBackStackEntryAsState()
                                val currentDestinatin=navBackStackStrace?.destination
                                navList.forEach {item->
                                    BottomNavigationItem(
                                        icon={
                                            if (item.badgeCount>0)
                                            {
                                                BadgeBox(badgeContent =
                                                {
                                                    Text(text =item.badgeCount.toString() )}) {
                                                    Icon(imageVector = item.icon, contentDescription = item.route)
                                                }
                                            } else
                                            {
                                                Icon(imageVector = item.icon, contentDescription = item.route)
                                            }

                                             },
                                        label={ Text(text = item.label)},
                                        unselectedContentColor= Color.Yellow,
                                        selected = currentDestinatin?.hierarchy?.any{it.route==item.route}==true,
                                        onClick = { navController.navigate(item.route) })
                                }
                            }
                    }
                ) {
                        NavHost(navController = navController, startDestination =Screen.Home.route ){
                            composable(Screen.Home.route){
                                Home(navController =navController)
                            }
                            composable(Screen.Message.route){
                                Message(navController = navController)
                            }
                            composable(Screen.Setting.route){
                                Setting(navController = navController)
                            }
                        }
                }
            }
        }
    }
}

