package com.capuras.listfragment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title : String,
    val details : String,
    val imageUrl : String,
)  : Parcelable

