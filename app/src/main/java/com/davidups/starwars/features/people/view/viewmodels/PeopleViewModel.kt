package com.davidups.starwars.features.people.view.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.davidups.starwars.core.extensions.cancelIfActive
import com.davidups.starwars.core.interactor.UseCase
import com.davidups.starwars.core.functional.Error
import com.davidups.starwars.core.functional.Success
import com.davidups.starwars.core.platform.BaseViewModel
import com.davidups.starwars.features.people.models.data.People
import com.davidups.starwars.features.people.models.view.PeopleView
import com.davidups.starwars.features.people.models.view.PersonDetail
import com.davidups.starwars.features.people.usecases.GetFavorites
import com.davidups.starwars.features.people.usecases.GetPeople
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PeopleViewModel(private val getPeople: GetPeople,
                      private val getFavorites: GetFavorites) : BaseViewModel() {

    var people = MutableLiveData<People>()
    var peopleView = MutableLiveData<PeopleView>()
    var favoritePeople = MutableLiveData<List<PersonDetail>>()
    private var getMoviesJob: Job? = null
    private var getFavoritesJob: Job? = null

    fun getPeople() {
        getMoviesJob.cancelIfActive()
        getMoviesJob = viewModelScope.launch {
            getPeople(UseCase.None())
                .onStart { handleShowSpinner(true) }
                .onEach { handleShowSpinner(false) }
                .catch { failure -> handleFailure(failure) }
                .collect { result ->
                    when (result) {
                        is Success<People> -> {
                            people.value = result.data
                            peopleView.value = result.data.toPeopleView()
                        }
                        is Error -> {
                        }
                    }
                }
        }
        getFavorites()
    }

    fun getFavorites() {
        getFavoritesJob.cancelIfActive()
        getFavoritesJob = viewModelScope.launch {
            getFavorites(UseCase.None())
                .onStart { handleShowSpinner(true) }
                .onEach { handleShowSpinner(false) }
                .catch { failure -> handleFailure(failure) }
                .collect { favoritePeople.value = it }
        }
    }
}
