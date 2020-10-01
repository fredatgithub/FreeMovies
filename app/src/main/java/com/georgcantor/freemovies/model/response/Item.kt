package com.georgcantor.freemovies.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    var etag: String? = null,
    var id: String? = null,
    var kind: String? = null,
    var snippet: Snippet? = null
) : Parcelable