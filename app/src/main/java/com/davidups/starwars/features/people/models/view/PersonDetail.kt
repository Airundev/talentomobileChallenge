package com.davidups.starwars.features.people.models.view

import android.os.Parcelable
import com.davidups.starwars.core.database.entity.PersonDBEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonDetail(
    val name: String,
    val birthYear: String,
    val gender: String,
    val height: String,
    var isFavorite: Boolean
): Parcelable {

    fun toDBEntity() = PersonDBEntity(name.hashCode(), name, birthYear, gender, height)

    fun toPersonView() = PersonView(name)
}
