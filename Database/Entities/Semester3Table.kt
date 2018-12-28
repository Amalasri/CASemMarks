package com.example.admin.casemmarks.Database.Entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/*@Entity(
    tableName = "Semester3Table", foreignKeys = [
        ForeignKey(
            entity = StudentBiodataTable::class,
            parentColumns = ["id"],
            childColumns = ["StudentRollno"],
            onDelete = ForeignKey.CASCADE
        )]
)*/
@Entity(tableName = "Semester3Table")
class Semester3Table {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name = "StudentRollno")
    var StudentRollno: String? = null


    @ColumnInfo(name="DataStructure")
    var DataStructure: String?=null

    @ColumnInfo(name="Java")
    var Java: String?=null

    @ColumnInfo(name="JavaPractical")
    var JavaPractical: String?=null

    @ColumnInfo(name="MicroProcessor")
    var MicroProcessor: String?=null

    @ColumnInfo(name="Total")
    var Total: String?=null

    @ColumnInfo(name="Average")
    var Average: String?=null
}