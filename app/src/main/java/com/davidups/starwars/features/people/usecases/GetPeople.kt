package com.davidups.starwars.features.people.usecases

import com.davidups.starwars.core.interactor.UseCase
import com.davidups.starwars.core.functional.State
import com.davidups.starwars.features.people.models.data.People

class GetPeople(private val peopleRepository: PeopleRepository) : UseCase<State<People>, UseCase.None>() {
    override fun run(params: None?) = peopleRepository.getPeople()
}
