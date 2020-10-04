package com.georgcantor.freemovies.model.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnails(
    var standard: Standard? = null
) : Parcelable