package com.davidups.starwars.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.davidups.starwars.core.database.entity.PersonDBEntity

@Dao
interface PersonDao {
    @Query("SELECT * FROM persondbentity")
    fun getAll(): List<PersonDBEntity>

    @Insert
    fun insert(vararg person: PersonDBEntity?)

    @Delete
    fun delete(person: PersonDBEntity?)
}