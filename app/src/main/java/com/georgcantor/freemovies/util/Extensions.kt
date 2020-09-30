package com.georgcantor.freemovies.util

import android.content.Context
import android.net.ConnectivityManager

fun Context.isNetworkAvailable() = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?)
    ?.activeNetworkInfo?.isConnectedOrConnecting ?: false
