package com.georgcantor.freemovies.util

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
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

fun AppCompatActivity.openFragment(fragment: Fragment, replace: Boolean) =
    supportFragmentManager.beginTransaction().apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) setCustomAnimations(
            R.anim.pull_in_right,
            R.anim.push_out_left,
            R.anim.pull_in_left,
            R.anim.push_out_right
        )
        if (replace) {
            replace(R.id.fragment, fragment)
        } else {
            add(R.id.fragment, fragment)
            addToBackStack(null)
        }
        commit()
    }

fun View.visible() { visibility = View.VISIBLE }

fun View.gone() { visibility = View.GONE }

fun Long.runDelayed(action: () -> Unit) {
    Handler(getMainLooper()).postDelayed(action, TimeUnit.MILLISECONDS.toMillis(this))
}