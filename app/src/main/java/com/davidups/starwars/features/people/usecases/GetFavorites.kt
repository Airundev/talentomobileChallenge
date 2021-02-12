package com.davidups.starwars.features.people.usecases

import com.davidups.starwars.core.interactor.UseCase
import com.davidups.starwars.core.functional.State
import com.davidups.starwars.features.people.models.data.People
import com.davidups.starwars.features.people.models.view.PersonDetail
import kotlinx.coroutines.flow.Flow

class GetFavorites(private val personRepository: PersonRepository) : UseCase<List<PersonDetail>, UseCase.None>() {
    override fun run(params: None?): Flow<List<PersonDetail>> = personRepository.getFavorites()
}
