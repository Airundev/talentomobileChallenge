package com.davidups.starwars.features.people.models.view

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonDetailView(
    val name: String,
    val birthYear: String,
    val gender: String,
    val height: String
): Parcelable
