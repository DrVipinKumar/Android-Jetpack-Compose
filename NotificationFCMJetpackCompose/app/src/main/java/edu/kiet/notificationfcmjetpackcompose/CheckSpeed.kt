package edu.kiet.notificationfcmjetpackcompose

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build

class CheckSpeed() {
    companion object {

        // returns 102 Mbps
        fun getNetworkSpeed(context: Context): String {
            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val nc = cm.getNetworkCapabilities(cm.activeNetwork)
                val downSpeed = (nc?.linkDownstreamBandwidthKbps)?.div(1000)
                "${downSpeed ?: 0} Mbps"
            } else {
                "-"
            }
        }
    }
}
