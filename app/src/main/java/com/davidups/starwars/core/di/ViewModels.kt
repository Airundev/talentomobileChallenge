package com.davidups.starwars.core.di

import com.davidups.starwars.features.people.view.viewmodels.PeopleViewModel
import com.davidups.starwars.features.person.view.viewmodels.PersonViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { PeopleViewModel(get()) }
    viewModel { PersonViewModel(get(), get()) }

}
