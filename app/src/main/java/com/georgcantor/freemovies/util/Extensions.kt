package com.georgcantor.freemovies.util

import android.content.Context
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Looper.getMainLooper
import android.view.View
import android.widget.ImageView
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.georgcantor.freemovies.R
import java.util.concurrent.TimeUnit

fun Context.isNetworkAvailable() = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?)
    ?.activeNetworkInfo?.isConnectedOrConnecting ?: false

fun Context.loadImage(url: String, view: ImageView) = Glide.with(this)
    .load(url)
    .placeholder(android.R.color.black)
    .thumbnail(0.1F)
    .into(view)

fun Context.shortToast(message: String) = makeText(this, message, LENGTH_SHORT).show()

fun AppCompatActivity.replaceFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.fragment, fragment)
        commit()
    }

fun AppCompatActivity.addFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
    add(R.id.fragment, fragment)
    addToBackStack(null)
    commit()
}

fun View.visible() { visibility = View.VISIBLE }

fun View.gone() { visibility = View.GONE }

fun Long.runDelayed(action: () -> Unit) {
    Handler(getMainLooper()).postDelayed(action, TimeUnit.MILLISECONDS.toMillis(this))
}