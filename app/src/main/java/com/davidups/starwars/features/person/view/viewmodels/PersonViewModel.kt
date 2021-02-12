package com.davidups.starwars.features.person.view.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.davidups.starwars.core.platform.BaseViewModel
import com.davidups.starwars.features.people.models.view.PersonDetail
import com.davidups.starwars.features.people.usecases.DeleteFavorite
import com.davidups.starwars.features.people.usecases.SaveFavorite
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PersonViewModel(private val saveFavorite: SaveFavorite,
                      private val deleteFavorite: DeleteFavorite) : BaseViewModel() {

    var isFavorite = MutableLiveData<Boolean>()

    private var favoriteJob: Job? = null

    fun checkIfFavorite(personDetail: PersonDetail?) {
        isFavorite.value = personDetail?.isFavorite
    }

    fun saveFavorite(personDetail: PersonDetail?) {
        favoriteJob = viewModelScope.launch { saveFavorite.run(personDetail).collect { isFavorite.value = true } }
    }

    fun deleteFavorite(personDetail: PersonDetail?) {
        favoriteJob = viewModelScope.launch { deleteFavorite.run(personDetail).collect { isFavorite.value = false } }
    }
}
