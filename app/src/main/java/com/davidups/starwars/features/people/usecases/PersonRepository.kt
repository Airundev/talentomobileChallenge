package com.davidups.starwars.features.people.usecases

import com.davidups.starwars.core.functional.Error
import com.davidups.starwars.features.people.models.view.PersonDetail
import com.davidups.starwars.features.people.services.PersonService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface PersonRepository {

    fun saveFavorite(personDetail: PersonDetail?): Flow<Unit>

    fun deleteFavorite(personDetail: PersonDetail?): Flow<Unit>

    fun getFavorites(): Flow<List<PersonDetail>>

    class Database(
        private val ioDispatcher: CoroutineDispatcher,
        private val service: PersonService
    ) : PersonRepository {

        override fun saveFavorite(personDetail: PersonDetail?): Flow<Unit> =
                flow { emit(favorite(personDetail)) }.flowOn(ioDispatcher)

        override fun deleteFavorite(personDetail: PersonDetail?): Flow<Unit> =
                flow { emit(delete(personDetail)) }.flowOn(ioDispatcher)

        override fun getFavorites(): Flow<List<PersonDetail>> =
                flow { emit(getFavoritesFromDatabase()) }.flowOn(ioDispatcher)

        private fun favorite(personDetail: PersonDetail?): Unit = service.saveFavorite(personDetail)

        private fun delete(personDetail: PersonDetail?): Unit = service.deleteFavorite(personDetail)

        private fun getFavoritesFromDatabase(): List<PersonDetail> =
                service.getFavorites().map { it.toPersonDetail() }.toMutableList()
    }
}
