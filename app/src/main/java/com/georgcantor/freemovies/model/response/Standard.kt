package com.georgcantor.freemovies.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Standard(
    var height: Long = 0,
    var url: String? = null,
    var width: Long = 0
) : Parcelable