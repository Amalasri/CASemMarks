package com.example.admin.casemmarks.Database.Daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.example.admin.casemmarks.Database.Entities.LoginTable

@Dao
interface  LoginDao {
    @Insert
    fun insert(Logintable: LoginTable)

    @Query("SELECT * FROM LoginTable")
    fun getAll(): List<LoginTable>
}







