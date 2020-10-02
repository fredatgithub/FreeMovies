package com.georgcantor.freemovies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.georgcantor.freemovies.R
import com.georgcantor.freemovies.util.NetworkUtils.getNetworkLiveData
import com.georgcantor.freemovies.util.gone
import com.georgcantor.freemovies.util.visible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setupWithNavController(findNavController(R.id.nav_host_fragment))

        getNetworkLiveData(applicationContext).observe(this) { isConnected ->
            no_internet_warning.apply {
                when (isConnected) {
                    true -> if (isVisible) gone()
                    false -> if (isGone) visible()
                }
            }
        }
    }
}