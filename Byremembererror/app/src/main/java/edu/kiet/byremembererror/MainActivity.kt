package edu.kiet.byremembererror

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.kiet.byremembererror.ui.theme.ByremembererrorTheme
import java.util.jar.Manifest
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ByremembererrorTheme {
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

@Composable
fun Greeting() {
    var check by remember { mutableStateOf(false)}
    Column(modifier = Modifier.fillMaxWidth(), 
        horizontalAlignment =Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            check=!check
        }) {
            Text(text = "Click Me to Show Text",
                fontWeight = FontWeight.Bold,
            fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (check){
            Text(text = "I display by click only", fontWeight = FontWeight.Bold,
                fontSize = 30.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ByremembererrorTheme {
        Greeting()
    }
}