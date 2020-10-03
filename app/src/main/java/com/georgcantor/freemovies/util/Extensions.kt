package com.georgcantor.freemovies.util

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.georgcantor.freemovies.R

fun Context.isNetworkAvailable() = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?)
    ?.activeNetworkInfo?.isConnectedOrConnecting ?: false

fun Context.loadImage(url: String, view: ImageView) = Glide.with(this)
    .load(url)
    .placeholder(android.R.color.darker_gray)
    .thumbnail(0.1F)
    .into(view)

fun AppCompatActivity.replaceFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.fragment, fragment)
        commit()
    }

fun AppCompatActivity.addFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
    add(R.id.fragment, fragment)
    addToBackStack(null)
    commit()
}

fun AppCompatActivity.popFragment() = supportFragmentManager.popBackStack()

fun View.visible() { visibility = View.VISIBLE }

fun View.gone() { visibility = View.GONE }