package edu.kiet.navigationjetpackcompose

sealed class Screen (val route:String){
    object FirstScreen:Screen("FirstScreen")
    object SecondScreen:Screen("SecondScreen/{info}/{info2}")
    object NestedScreen:Screen("NestedScreen")
    object NestedScreenRoute:Screen("NestedScreenRoute")
}
