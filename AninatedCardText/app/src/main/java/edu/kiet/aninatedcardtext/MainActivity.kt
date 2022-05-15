package edu.kiet.aninatedcardtext

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import edu.kiet.aninatedcardtext.ui.theme.AninatedCardTextTheme
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AninatedCardTextTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Greeting() {
 var expand by remember { mutableStateOf(false)}
    val rotate by animateFloatAsState(
        targetValue = if (expand) 180f else 0f )


    Card(modifier = Modifier
        .fillMaxWidth()
        .animateContentSize(
            animationSpec = tween(
               // durationMillis = 1000

            )

        )
        .padding(10.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
        ) {
            Row(modifier=Modifier.fillMaxWidth()) {
             Text(text = "Show Text",
                 Modifier.weight(6f),
                 fontSize = 20.sp,
                 fontWeight = FontWeight.Bold

             )
                IconButton(onClick = { expand=!expand },
                    Modifier
                        .weight(1f)
                        .rotate(rotate)
                ) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription ="arrow" )
                }
            }
            AnimatedVisibility(visible = expand,
                 enter=fadeIn(animationSpec = tween(
                      durationMillis = 2000
                 )),
                 exit=fadeOut(animationSpec = tween(
                     durationMillis = 2000))) {
                Box(modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)

                    )
                ){
                    Text(text = "Well, seems like a nice plugin and" +
                            " all, but it doesn't work on mac.. Or atleast, " +
                            "I can't get it to work :( Tried to open the \"Generate\" " +
                            "\by shortcut and by right-click - no such luck.. I won't be " +
                            "rating this plugin, since it \"don't\" work on Mac, so no " +
                            "worries -- won't destroy the ratings.. Atleast not " +
                            "untill I try the plugin lo",Modifier.padding(10.dp))
                }

            }

        }
    }

        

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AninatedCardTextTheme {
        Greeting()
    }
}