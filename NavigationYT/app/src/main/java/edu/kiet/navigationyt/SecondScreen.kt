package edu.kiet.navigationyt

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun SecondScreen(navController: NavController, output:String){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Second Screen", fontWeight = FontWeight.Bold,
            fontSize = 25.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.navigate(Screen.NavigationRoute.route) }) {
            Text(text = "Nested Screen", fontWeight = FontWeight.Bold,
                fontSize = 25.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = output, fontWeight = FontWeight.Bold,
            fontSize = 25.sp)
    }
}