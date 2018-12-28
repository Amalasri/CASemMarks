package com.example.admin.casemmarks.Database.Entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "logintable")
 class LoginTable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name="Email")
    var Email: String?=null

    @ColumnInfo(name="Password")
    var Password:String?=null

    @ColumnInfo(name="Category")
    var Category:String?=null
   }




