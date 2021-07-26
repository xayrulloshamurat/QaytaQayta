package com.example.qaytaqayta

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    companion object{
        lateinit var INSTANCE : MyDatabase
        fun getInstance(context: Context) : MyDatabase {
            if(!::INSTANCE.isInitialized){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,"username-database"
                )
                    .allowMainThreadQueries()
                    .createFromAsset("database.db")
                    .build()
            }
            return INSTANCE
        }
    }
    abstract fun usernameDao(): MyDao
}
