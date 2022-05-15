package edu.kiet.helloworldapp

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.kiet.helloworldapp.ui.theme.HelloWorldAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorldAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(
                        contentAlignment =Alignment.Center,
                        modifier =Modifier.background(Color.Cyan)

                    ) {
                        Column() {
                            Greeting("Jetpack")
                            Greeting("Compose")
                            Box(
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(200.dp)
                                    .background(Color.Red)
                            ) {

                            }
                        }



                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!",
        fontSize = 30.sp, color = Color.Red,
        modifier = Modifier.width(250.dp).background(MaterialTheme.colors.primary),
        textAlign = TextAlign.Center
       )
}
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    name="Dark Mode"
)
@Composable
fun DefaultPreview1() {
    HelloWorldAppTheme {
        Greeting("World of Jetpack Android")
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HelloWorldAppTheme {
        Greeting("World of Jetpack Android")
    }
}