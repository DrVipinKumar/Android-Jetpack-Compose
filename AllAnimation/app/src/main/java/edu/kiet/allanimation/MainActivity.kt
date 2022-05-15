package edu.kiet.allanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.kiet.allanimation.ui.theme.AllAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AllAnimationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Greeting() {
    var visible by remember { mutableStateOf(false)}

    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)

    ) {
        Button(onClick = { visible=!visible}) {
            Text(text = "Show Slide In/Slide Out Animation", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        AnimatedVisibility(visible = visible,
         enter= slideIn( initialOffset = {
               fullSize -> IntOffset(fullSize.width/2-20,fullSize.height/2-20)
         },
             animationSpec = tween(durationMillis = 3000, easing = LinearOutSlowInEasing)

         ),
            exit=slideOut( targetOffset = {
                    fullSize -> IntOffset(fullSize.width/2-20,fullSize.height/2-20)
            },
                animationSpec = tween(durationMillis = 3000, easing = LinearOutSlowInEasing)

            )
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                    .height(150.dp)
            ) {
                Text(text = "Show Slide In/Slide Out Animation," +
                        "Show Slide In/Slide Out Animation" +
                        "Show Slide In/Slide Out Animation", fontSize = 20.sp)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AllAnimationTheme {
        Greeting()
    }
}