package com.davidups.starwars.core.di

import com.davidups.starwars.features.people.usecases.DeleteFavorite
import com.davidups.starwars.features.people.usecases.GetFavorites
import com.davidups.starwars.features.people.usecases.GetPeople
import com.davidups.starwars.features.people.usecases.SaveFavorite
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetPeople(get()) }
    factory { SaveFavorite(get()) }
    factory { GetFavorites(get()) }
    factory { DeleteFavorite(get()) }
}
