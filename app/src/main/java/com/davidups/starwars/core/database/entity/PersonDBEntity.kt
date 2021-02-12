package com.davidups.starwars.core.database.entity

import androidx.room.*
import com.davidups.starwars.features.people.models.view.PersonDetail

@Entity
data class PersonDBEntity(
        @PrimaryKey(autoGenerate = true) var id: Int?,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "birthYear") val birthYear: String,
        @ColumnInfo(name = "gender") val gender: String,
        @ColumnInfo(name = "height") val height: String
) {
        fun toPersonDetail() = PersonDetail(name, birthYear, gender, height, true)
}