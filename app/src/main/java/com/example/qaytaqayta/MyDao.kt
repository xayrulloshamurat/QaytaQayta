package com.example.qaytaqayta

import androidx.room.*

@Dao
interface MyDao {
    @Query("SELECT * FROM username")
    fun getAllUsername(): List<User>

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}
