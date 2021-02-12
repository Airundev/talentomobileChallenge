package com.davidups.starwars.core.di

import com.davidups.starwars.features.people.usecases.PeopleRepository
import com.davidups.starwars.features.people.usecases.PersonRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<PeopleRepository> { PeopleRepository.Network(get(), get()) }
    factory<PersonRepository> { PersonRepository.Database(get(), get()) }

}
