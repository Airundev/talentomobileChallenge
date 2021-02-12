package com.davidups.starwars.features.person.view.viewmodels

import androidx.lifecycle.viewModelScope
import com.davidups.starwars.core.interactor.UseCase
import com.davidups.starwars.core.platform.BaseViewModel
import com.davidups.starwars.features.people.models.view.PersonDetail
import com.davidups.starwars.features.people.usecases.DeleteFavorite
import com.davidups.starwars.features.people.usecases.GetFavorites
import com.davidups.starwars.features.people.usecases.SaveFavorite
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PersonViewModel(private val saveFavorite: SaveFavorite,
                      private val deleteFavorite: DeleteFavorite) : BaseViewModel() {

    private var favoriteJob: Job? = null

    fun saveFavorite(personDetail: PersonDetail?) {
        favoriteJob = viewModelScope.launch { saveFavorite.run(personDetail) }
    }

    fun deleteFavorite(personDetail: PersonDetail?) {
        favoriteJob = viewModelScope.launch { deleteFavorite.run(personDetail) }
    }
}
