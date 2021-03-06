package com.davidups.starwars.core.di

import com.davidups.starwars.features.people.services.PeopleService
import com.davidups.starwars.features.people.services.PersonService
import org.koin.dsl.module

val dataSourceModule = module {

    factory { PeopleService(get()) }
    factory { PersonService(get()) }

}
