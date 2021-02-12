package com.davidups.starwars.core.di

import com.davidups.starwars.core.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {

    factory { AppDatabase.getAppDataBase(get()) }

}
