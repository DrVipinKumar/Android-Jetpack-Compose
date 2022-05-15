package edu.kiet.allanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.kiet.allanimation.ui.theme.AllAnimationTheme

class InfiniteAnimation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AllAnimationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ShowAnimation()
                }
            }
        }
    }
}

@Composable
fun ShowAnimation() {
    val InfiniteAnimation= rememberInfiniteTransition()
    val color by InfiniteAnimation.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(color))

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    AllAnimationTheme {
        ShowAnimation()
    }
}