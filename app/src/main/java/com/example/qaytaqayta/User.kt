package com.example.qaytaqayta

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "username")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    @ColumnInfo(name = "username")
    var username: String,
    @ColumnInfo(name = "surname")
    var surname: String
)
