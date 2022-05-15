package edu.kiet.notificationfcmjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import edu.kiet.notificationfcmjetpackcompose.ui.theme.NotificationFCMJetpackComposeTheme
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : ComponentActivity() {
    lateinit var message:MutableState<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EventBus.getDefault().register(this);
        setContent {
            NotificationFCMJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    message =remember { mutableStateOf("")}
                    FCMMessage()
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MyMessage?) {
        // Do something
       message.value= event?.message.toString()
    }
    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this);
    }
    @Composable
    fun FCMMessage() {

        val coroutineScope = rememberCoroutineScope()
        val context= LocalContext.current
        var speed by remember {mutableStateOf("")}
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            OutlinedButton(onClick = {
                val notish =MyNotification(context,"FCM","This is Notitification for FCM Testing")
                notish.FirNotification()

            }) {
                Text(text = "Fire Notification", fontSize = 16.sp)

            }
            AnimatedVisibility(visible = true) {
                Text(text = message.value.toString(), fontSize = MaterialTheme.typography.h3.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        NotificationFCMJetpackComposeTheme {
            FCMMessage()
        }
    }
}


