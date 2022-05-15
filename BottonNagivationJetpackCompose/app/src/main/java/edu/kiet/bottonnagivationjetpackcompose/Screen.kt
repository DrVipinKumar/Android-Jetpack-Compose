package edu.kiet.bottonnagivationjetpackcompose

sealed class Screen(val route:String){
    object Home:Screen("home")
    object Message:Screen("Message")
    object Setting:Screen("Setting")

}
