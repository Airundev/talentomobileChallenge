package com.davidups.starwars.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.davidups.starwars.core.database.dao.PersonDao
import com.davidups.starwars.core.database.entity.PersonDBEntity


@Database(entities = [PersonDBEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun personDao(): PersonDao


    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "starWarsDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}

