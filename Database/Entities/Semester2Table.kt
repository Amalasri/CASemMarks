package com.example.admin.casemmarks.Database.Entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/*@Entity(
    tableName = "Semester2Table", foreignKeys = [
        ForeignKey(
            entity = StudentBiodataTable::class,
            parentColumns = ["id"],
            childColumns = ["StudentRollno"],
            onDelete = ForeignKey.CASCADE
        )]
)*/
@Entity(tableName = "Semester2Table")
class Semester2Table {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name = "StudentRollno")
    var StudentRollno: String? = null

    @ColumnInfo(name="Tamil")
    var Tamil: String?=null

    @ColumnInfo(name="English")
    var English: String?=null

    @ColumnInfo(name="Oops")
    var Oops: String?=null

    @ColumnInfo(name="DiscreteMaths")
    var DiscreteMaths: String?=null

    @ColumnInfo(name="OopsPractical")
    var OopsPractical: String?=null

    @ColumnInfo(name="InternetBasics")
    var InternetBasics: String?=null

    @ColumnInfo(name="Total")
    var Total: String?=null

    @ColumnInfo(name="Average")
    var Average: String?=null
}