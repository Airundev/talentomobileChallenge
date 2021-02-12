package com.davidups.starwars.features.people.services

import com.davidups.starwars.core.database.AppDatabase
import com.davidups.starwars.features.people.models.view.PersonDetail

class PersonService(appDatabase: AppDatabase){

    private val personDatabase by lazy { appDatabase.personDao() }

    fun saveFavorite(personDetail: PersonDetail?) = personDatabase.insert(personDetail?.toDBEntity())

    fun deleteFavorite(personDetail: PersonDetail?) = personDatabase.delete(personDetail?.toDBEntity())

    fun getFavorites() = personDatabase.getAll()
}
