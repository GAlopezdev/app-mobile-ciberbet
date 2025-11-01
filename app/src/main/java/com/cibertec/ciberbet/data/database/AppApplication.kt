package com.cibertec.ciberbet.data.database


import android.app.Application
import androidx.room.Room

class AppApplication : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this,
            AppDatabase::class.java,
            "StoreDatabase")
            .build()
    }
}