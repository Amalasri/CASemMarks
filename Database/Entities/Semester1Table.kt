package com.example.admin.casemmarks.Database.Entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey

/*
@Entity(
    tableName = "Semester1Table", foreignKeys = [
        ForeignKey(
            entity = StudentBiodataTable::class,
            parentColumns = ["id"],
            childColumns = ["StudentRollno"],
            onDelete = CASCADE
        )]
)
*/

@Entity(tableName = "Semester1Table")
class Semester1Table {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name = "StudentRollno")
    var StudentRollno: String? = null

    @ColumnInfo(name="Tamil")
    var Tamil: String?=null

    @ColumnInfo(name="English")
    var English: String?=null

    @ColumnInfo(name="Cprogram")
    var Cprogram: String?=null

    @ColumnInfo(name="Maths")
    var Maths: String?=null

    @ColumnInfo(name="Digital")
    var Digital: String?=null

    @ColumnInfo(name="Cpractical")
    var Cpractical: String?=null

    @ColumnInfo(name="Total")
    var Total: String?=null

    @ColumnInfo(name="Average")
    var Average: String?=null

}