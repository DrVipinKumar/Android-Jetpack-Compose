package edu.kiet.notificationfcmjetpackcompose

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.greenrobot.eventbus.EventBus


class FCMService : FirebaseMessagingService() {
     val tag:String="FCMToken"
    override fun onNewToken(token: String) {
        Log.d(tag, "FCMToken: $token")

    }
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // ...

         // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(tag, "Message data payload: ${remoteMessage.data}")

        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d(tag, "Message Notification Body: ${it.body}")
//            val notish =MyNotification(applicationContext,it.title.toString(),it.body.toString())
//            notish.FirNotification()
            EventBus.getDefault().post(MyMessage(it.body.toString()))
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
}