package edu.kiet.roomjetpackcompose.roomdata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Student")
data class Student (
    @PrimaryKey
    var rollno:Int,
    var studentName:String,
    var course:String

)