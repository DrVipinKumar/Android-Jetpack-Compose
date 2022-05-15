package edu.kiet.navigationyt

sealed class Screen(val route:String){
    object ScreenOne:Screen("ScreenOne")
    object ScreenTwo:Screen("ScreenTwo?info={info}")
    object NavigationRoute:Screen("Navigation")
    object NestedScreenOne:Screen("NestedOne")
    object NestedScreenTwo:Screen("NestedTwo")
}
