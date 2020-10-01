package com.georgcantor.freemovies.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ResourceId : Parcelable {
    var kind: String? = null
    var videoId: String? = null
}