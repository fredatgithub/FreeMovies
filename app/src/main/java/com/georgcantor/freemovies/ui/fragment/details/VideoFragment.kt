package com.georgcantor.freemovies.ui.fragment.details

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_VISIBLE
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.georgcantor.freemovies.R
import com.georgcantor.freemovies.util.Constants.PLAYLIST_ID
import com.georgcantor.freemovies.util.gone
import com.georgcantor.freemovies.util.visible
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_video.*

class VideoFragment : Fragment(R.layout.fragment_video) {

    companion object {
        fun create(id: String?) = VideoFragment().apply {
            arguments = Bundle().apply { putString(PLAYLIST_ID, id) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(player_view) {
            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(arguments?.get(PLAYLIST_ID) as String, 0f)
                }
            })

            getPlayerUiController()
                .setFullScreenButtonClickListener {
                    when (isFullScreen()) {
                        true -> setPortraitMode()
                        false -> setLandscapeMode()
                    }
                }
        }
    }

    override fun onPause() {
        super.onPause()
        player_view?.release()
    }

    override fun onDestroyView() {
        with(requireActivity()) {
            nav_view?.visible()
            requestedOrientation = SCREEN_ORIENTATION_PORTRAIT
            window?.decorView?.systemUiVisibility = SYSTEM_UI_FLAG_VISIBLE
        }
        super.onDestroyView()
    }

    private fun setPortraitMode() {
        player_view?.exitFullScreen()
        with(requireActivity()) {
            nav_view?.visible()
            requestedOrientation = SCREEN_ORIENTATION_PORTRAIT
            window?.decorView?.systemUiVisibility = SYSTEM_UI_FLAG_VISIBLE
        }
    }

    private fun setLandscapeMode() {
        player_view?.enterFullScreen()
        with(requireActivity()) {
            nav_view?.gone()
            requestedOrientation = SCREEN_ORIENTATION_LANDSCAPE
            window?.decorView?.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        }
    }
}