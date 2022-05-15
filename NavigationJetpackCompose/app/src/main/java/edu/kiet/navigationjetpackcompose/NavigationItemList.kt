package edu.kiet.navigationjetpackcompose

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItemList(
    var icon:ImageVector,
    var label:String,
    var route:String,
    var badgeCount:Int=0
)

val navList=listOf<NavigationItemList>(
    NavigationItemList(Icons.Default.Home,"Home",Screen.FirstScreen.route),
    NavigationItemList(Icons.Default.Notifications,"Message",Screen.SecondScreen.route,200),
    NavigationItemList(Icons.Default.Settings,"Settings",Screen.NestedScreen.route)
)
