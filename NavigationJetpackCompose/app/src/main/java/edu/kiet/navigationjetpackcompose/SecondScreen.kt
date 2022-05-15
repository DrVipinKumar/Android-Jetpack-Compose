package edu.kiet.navigationjetpackcompose

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
fun SecondScreen(navController: NavController, info:String){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment =Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Second Screen",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = {navController.navigate(Screen.NestedScreenRoute.route)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            ) {
            Text(text = "Nested Screen")
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(text=info,fontSize = 25.sp,
            fontWeight = FontWeight.Bold)
    }
}