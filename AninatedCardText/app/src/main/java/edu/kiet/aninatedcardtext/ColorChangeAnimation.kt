package edu.kiet.aninatedcardtext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.kiet.aninatedcardtext.ui.theme.AninatedCardTextTheme

class ColorChangeAnimation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AninatedCardTextTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting2()
                }
            }
        }
    }
}

@Composable
fun Greeting2() {
    var checked by remember { mutableStateOf(false)}
    val animatedColor by animateColorAsState(
        targetValue = if (checked) Color.Blue else Color.Red)
   Column(modifier = Modifier
       .fillMaxWidth()
       .padding(10.dp)
   ) {
       Button(onClick = { checked=!checked }) {
           Text(text = "Change Color", fontSize = 20.sp, fontWeight = FontWeight.Bold)
       }
       Box(modifier = Modifier.fillMaxWidth()
           .background(color=animatedColor)
           .padding(20.dp)
           .height(100.dp)


       )

        {

       }
   }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    AninatedCardTextTheme {
        Greeting2()
    }
}