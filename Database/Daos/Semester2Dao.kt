package com.example.admin.casemmarks.Database.Daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.admin.casemmarks.Database.Entities.Semester1Table
import com.example.admin.casemmarks.Database.Entities.Semester2Table

@Dao
interface Semester2Dao {

    @Insert
    fun insert(Semester2Table: Semester2Table)

    @Query("SELECT * FROM Semester2Table WHERE StudentRollno = :StudentRollno")
    fun findBySemester2(StudentRollno: String): List<Semester2Table>

    @Query("SELECT * FROM Semester2Table")
    fun getAll(): List<Semester2Table>

}