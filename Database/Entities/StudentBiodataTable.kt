package com.example.admin.casemmarks.Database.Entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "StudentBiodataTable")
class StudentBiodataTable{

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name="StudentRollno")
    var StudentRollno: String?=null

    @ColumnInfo(name="StudentName")
    var StudentName: String?=null

    @ColumnInfo(name="DOB")
    var DOB: String?=null

    @ColumnInfo(name="Email")
    var Email: String?=null

    @ColumnInfo(name="Gender")
    var Gender: String?=null

    @ColumnInfo(name="Bloodgroup")
    var Bloodgroup: String?=null

    @ColumnInfo(name="ResidentialAddress")
    var ResidentialAddress: String?=null

    @ColumnInfo(name="MobileNo")
    var MobileNo: String?=null

    @ColumnInfo(name="FatherName")
    var FatherName: String?=null

    @ColumnInfo(name="MotherName")
    var MotherName: String?=null

    @ColumnInfo(name="FatherOccupation")
    var FatherOccupation: String?=null

    @ColumnInfo(name="MotherOccupation")
    var MotherOccupation: String?=null

    @ColumnInfo(name="FatherMobileno")
    var FatherMobileno: String?=null

    @ColumnInfo(name="MotherMobileno")
    var MotherMobileno: String?=null

    @ColumnInfo(name="PermanentAddress")
    var PermanentAddress: String?=null

    @ColumnInfo(name="Degree")
    var Degree: String?=null

    @ColumnInfo(name="Batch")
    var Batch: String?=null

    @ColumnInfo(name="StudentCategory")
    var StudentCategory: String?=null

}