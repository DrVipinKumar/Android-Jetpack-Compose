package edu.kiet.datasealedapp

sealed class Area
{
    fun display():String{
        return "Hello I am in Sealed Class"
    }

    class Circle:Area() {
        fun getArea(r:Int):String{
            return "Area =${3.14*r*r}"
        }

    }
}

