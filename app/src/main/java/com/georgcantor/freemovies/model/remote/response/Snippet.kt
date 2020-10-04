package com.georgcantor.freemovies.model.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Snippet(
    var channelId: String? = null,
    var channelTitle: String? = null,
    var description: String? = null,
    var playlistId: String? = null,
    var position: Long = 0,
    var publishedAt: String? = null,
    var resourceId: ResourceId? = null,
    var thumbnails: Thumbnails? = null,
    var title: String? = null
) : Parcelable