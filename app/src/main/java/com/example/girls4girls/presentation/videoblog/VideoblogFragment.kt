package com.example.girls4girls.presentation.videoblog

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.ConnectivityManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.os.IResultReceiver
import android.util.DisplayMetrics
import android.util.Log
import android.util.SparseArray
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.girls4girls.databinding.FragmentVideoblogBinding
import com.example.girls4girls.presentation.MainActivity
import com.example.girls4girls.presentation.videoblogsList.VideoblogsListFragment.Companion.TAG
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController

class VideoblogFragment : Fragment() {

    private val viewModel by viewModels<VideoblogViewModel>()
    private lateinit var binding: FragmentVideoblogBinding

    private lateinit var playerParams: ViewGroup.LayoutParams

    private val args by navArgs<VideoblogFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoblogBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playerParams = binding.player.layoutParams

        if (viewModel.defaultHeight == null){
            viewModel.defaultHeight = playerParams.height
        }

        setText()

        initPlayer()

        addFullScreenListener()

    }

    private fun setText() {
        binding.videoTitleTxt.text = args.currentVideoBlog.title
        binding.videoSpeakerTxt.text = args.currentVideoBlog.speaker
        binding.videoViewsTxt.text = args.currentVideoBlog.views.toString()

        binding.descriptionTxt.text = args.currentVideoBlog.description
    }

    private fun initPlayer() {

        // Make player observe lifecycle
        lifecycle.addObserver(binding.player)

        val listener = object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)

                Log.d(TAG, "Ready")

                val defaultPlayerUiController = DefaultPlayerUiController(binding.player, youTubePlayer)
                binding.player.setCustomPlayerUi(defaultPlayerUiController.rootView)

                val link = args.currentVideoBlog.link

                val videoID = link.substring(link.indexOf("=")+1)
                youTubePlayer.loadVideo(videoID,0F)
            }
        }

        val options = IFramePlayerOptions.Builder().controls(0).build()

        binding.player.initialize(listener, options)
    }

    private fun addFullScreenListener() {
        binding.player.addFullScreenListener(fullScreenListener = object:
            YouTubePlayerFullScreenListener{
            override fun onYouTubePlayerEnterFullScreen() {

                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }

            override fun onYouTubePlayerExitFullScreen() {

                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }

        })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        Log.d(TAG, "onConfigurationChanged: ${viewModel.isFullscreen}")

        viewModel.isFullscreen = !viewModel.isFullscreen
        toggleSystemUi()

    }

    @SuppressLint("InlinedApi")
    private fun toggleSystemUi() {

        val controller =  WindowInsetsControllerCompat(requireActivity().window, binding.player)
        val marginParams = playerParams as ViewGroup.MarginLayoutParams

        if (viewModel.isFullscreen){

            // Hide system UI
            (activity as AppCompatActivity).supportActionBar?.hide()
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

            // Set to fullscreen height
            playerParams.height = ViewGroup.LayoutParams.MATCH_PARENT

            // Remove margins
            (playerParams as ViewGroup.MarginLayoutParams).marginStart = 0
            (playerParams as ViewGroup.MarginLayoutParams).marginEnd = 0
            (playerParams as ViewGroup.MarginLayoutParams).topMargin = 0

            binding.scrollView.visibility = View.GONE


        } else {

            // Show system UI
            (activity as AppCompatActivity).supportActionBar?.show()
            controller.show(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

            // Set to normal height
            playerParams.height = viewModel.defaultHeight!!

            // Set Margins
            (playerParams as ViewGroup.MarginLayoutParams).marginStart = toDP(32)
            (playerParams as ViewGroup.MarginLayoutParams).marginEnd = toDP(32)
            (playerParams as ViewGroup.MarginLayoutParams).topMargin = toDP(65)

            binding.scrollView.visibility = View.VISIBLE
        }
    }

    fun toDP(pixel: Int): Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            pixel.toFloat(),
            context?.resources?.displayMetrics).toInt()
    }


    override fun onDestroy() {

        if (!viewModel.isFullscreen){
            super.onDestroy()
            Log.d(TAG, "Destroy")
            binding.player.release()
        }
        
    }

    companion object {
        val URI =
            "https://www.youtube.com/watch?v=gXWXKjR-qII"
        val TAG = "Chura"
    }

}