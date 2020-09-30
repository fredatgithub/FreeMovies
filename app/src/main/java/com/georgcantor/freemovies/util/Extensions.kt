package com.georgcantor.freemovies.util

import android.content.Context
import android.net.ConnectivityManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.georgcantor.freemovies.R

fun Context.isNetworkAvailable() = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?)
    ?.activeNetworkInfo?.isConnectedOrConnecting ?: false

fun Context.loadImage(url: String?, view: ImageView) = Glide.with(this)
    .load(url)
    .placeholder(R.drawable.ic_launcher_background)
    .thumbnail(0.1F)
    .into(view)