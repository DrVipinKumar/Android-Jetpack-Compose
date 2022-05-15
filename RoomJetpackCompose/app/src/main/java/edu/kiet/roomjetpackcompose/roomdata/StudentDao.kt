package edu.kiet.roomjetpackcompose.roomdata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {
    @Insert
    suspend fun insert(student:Student)
    @Query("select * from Student")
    suspend fun display():List<Student>
    @Query("select * from Student where rollno=:rno")
    suspend fun check(rno:Int):List<Student>
    @Update
    suspend fun update(student: Student)
    @Query("delete from Student where rollno=:rno")
    suspend fun delete(rno:Int)
}