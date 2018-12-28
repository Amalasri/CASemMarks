package com.example.admin.casemmarks.Database.Daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.admin.casemmarks.Database.Entities.Semester2Table
import com.example.admin.casemmarks.Database.Entities.Semester3Table

@Dao
interface Semester3Dao {

    @Insert
    fun insert(Semester3Table: Semester3Table)

    @Query("SELECT * FROM Semester3Table WHERE StudentRollno = :StudentRollno")
    fun findBySemester3(StudentRollno: String): List<Semester3Table>

    @Query("SELECT * FROM Semester3Table")
    fun getAll(): List<Semester3Table>
}