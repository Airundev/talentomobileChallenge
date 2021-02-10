package com.davidups.starwars.core.di

import com.davidups.starwars.features.people.usecases.GetPeople
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetPeople(get()) }

}
