package com.example.admin.casemmarks.Database.Daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.admin.casemmarks.Database.Entities.Semester1Table
import com.example.admin.casemmarks.Database.Entities.StudentBiodataTable


@Dao
interface Semester1Dao {

    @Insert
    fun insert(Semester1Table: Semester1Table)

    @Query("SELECT * FROM Semester1Table WHERE StudentRollno = :StudentRollno")
    fun findBySemester1(StudentRollno: String):List<Semester1Table>

    @Query("SELECT * FROM Semester1Table")
    fun getAll(): List<Semester1Table>

}
