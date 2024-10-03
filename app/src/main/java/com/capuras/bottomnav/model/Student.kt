package com.capuras.bottomnav.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student (
    val id : String,
    val lastName : String,
    val imageUri : String,
    val givenName : String,
    val middleName : String,
    val course : String,
    val year : String,
    val sem : String
) : Parcelable

