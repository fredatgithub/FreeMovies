package com.georgcantor.freemovies.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnails(
    var standard: Standard? = null
) : Parcelable