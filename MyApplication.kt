package com.example.admin.casemmarks

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.example.admin.casemmarks.Database.AppDatabase
import com.example.admin.casemmarks.Database.Entities.LoginTable


class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()
       /* doAsync {
            val database = AppDatabase.getInstance(context = this@MyApplication)

        }
*/

    }
}
