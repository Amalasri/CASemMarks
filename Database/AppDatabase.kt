package com.example.admin.casemmarks.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.admin.casemmarks.Database.Daos.*
import com.example.admin.casemmarks.Database.Entities.*

@Database(
    entities = [(LoginTable::class), (StudentBiodataTable::class), (Semester1Table::class),
        (Semester2Table::class), (Semester3Table::class)], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun LoginDao(): LoginDao
    abstract fun StudentBiodataDao(): StudentBiodataDao
    abstract fun Semester1Dao(): Semester1Dao
    abstract fun Semester2Dao(): Semester2Dao
    abstract fun Semester3Dao(): Semester3Dao

    /* companion object {
         private var sInstance: AppDatabase? = null

         @Synchronized
         fun getInstance(context: Context): AppDatabase {
             if (sInstance == null) {
                 sInstance = Room
                     .databaseBuilder(context.applicationContext, AppDatabase::class.java, "example")
                     .fallbackToDestructiveMigration()
                     .build()
             }
             return sInstance!!
         }
         fun destroyDataBase(){
             sInstance = null
         }*/
}


