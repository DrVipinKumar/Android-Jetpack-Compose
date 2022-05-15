package edu.kiet.bottonnagivationjetpackcompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItemList(
    var icon:ImageVector,
    val label:String,
    var route:String,
    var badgeCount:Int=0
)

var navList= listOf<NavItemList>(
    NavItemList(Icons.Default.Home,"Home",Screen.Home.route),
    NavItemList(Icons.Default.Notifications,"Message",Screen.Message.route,200),
    NavItemList(Icons.Default.Settings,"Setting",Screen.Setting.route),
)
