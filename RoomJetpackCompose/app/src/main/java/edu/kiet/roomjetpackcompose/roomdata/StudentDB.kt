package edu.kiet.roomjetpackcompose.roomdata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class],version=1)
abstract class StudentDB:RoomDatabase() {
    abstract fun getStudentDao():StudentDao
    companion object {
         var INSTANCE:StudentDB?=null
        fun getInstance(context: Context):StudentDB {
            if (INSTANCE==null){
                INSTANCE= Room.databaseBuilder(context.applicationContext,StudentDB::class.java,"studentdb.db").build()
            }
            return INSTANCE!!
        }

    }
}