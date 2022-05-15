package edu.kiet.textselectionjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.kiet.textselectionjetpackcompose.ui.theme.TextSelectionJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextSelectionJetpackComposeTheme {
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

    SelectionContainer() {
        Column() {
            Text(text = "Jetpack Compose is Android’s modern " +
                    "toolkit for building native UI. It simplifies and accelerates" +
                    " UI development on Android. Quickly bring your app to life with " +
                    "less code, powerful tools, and intuitive Kotlin APIs.")
            Spacer(modifier = Modifier.height(20.dp))
            DisableSelection {
                Text(text = "You can not select me, Try if you can ")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "You can select me without any problem")
        }


    }




    }



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TextSelectionJetpackComposeTheme {
        Greeting()
    }
}