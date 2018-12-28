package com.example.admin.casemmarks.Database.Daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.admin.casemmarks.Database.Entities.LoginTable
import com.example.admin.casemmarks.Database.Entities.Semester1Table
import com.example.admin.casemmarks.Database.Entities.StudentBiodataTable

@Dao
interface StudentBiodataDao {

    @Insert
    fun insert(StudentBiodataTable: StudentBiodataTable)

    @Query("SELECT * FROM StudentBiodataTable")
    fun getAll(): List<StudentBiodataTable>

    @Query("SELECT * FROM StudentBiodataTable WHERE StudentRollno = :StudentRollno")
    fun findstudent(StudentRollno: String):List<StudentBiodataTable>
}
