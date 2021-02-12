package com.davidups.starwars.features.people.usecases

import com.davidups.starwars.core.interactor.UseCase
import com.davidups.starwars.core.functional.State
import com.davidups.starwars.features.people.models.data.People
import com.davidups.starwars.features.people.models.view.PersonDetail
import kotlinx.coroutines.flow.Flow

class SaveFavorite(private val personRepository: PersonRepository) : UseCase<Unit, PersonDetail>() {
    override fun run(params: PersonDetail?): Flow<Unit> = personRepository.saveFavorite(params)
}
