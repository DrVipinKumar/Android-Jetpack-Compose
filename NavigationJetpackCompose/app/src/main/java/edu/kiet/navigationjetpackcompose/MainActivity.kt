package edu.kiet.navigationjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import edu.kiet.navigationjetpackcompose.ui.theme.NavigationJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination =Screen.FirstScreen.route ){
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
                        nestedRun(navController = navController)
                    }
                }
            }
        }
    }
}

