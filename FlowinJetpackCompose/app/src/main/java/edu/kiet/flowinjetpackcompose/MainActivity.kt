package edu.kiet.flowinjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.kiet.flowinjetpackcompose.ui.theme.FlowinJetpackComposeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowinJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val flowvalue =mutableStateListOf<Int>()
                   // val flowdata = listOf(1,2,3,4,5,6,7,8,9,10).asFlow().onEach { delay(1000) }
                    val flowdata = channelFlow {
                        (0..10).forEach { it ->
                            delay(1000)
                            send(it)
                        }
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        flowdata.filter { it -> it > 3 && it<8 }.collect { value -> flowvalue.add(value) }
                    }

                    Greeting(flowvalue)
                }
            }
        }
    }
}

@Composable
fun Greeting(value:List<Int>) {
     LazyColumn(){
         items(value){
             it -> Text("Flow Value =${it}")
         }
     }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FlowinJetpackComposeTheme {
        Greeting(listOf(1,2,4))
    }
}