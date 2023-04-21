package com.example.girls4girls.presentation.videoblog

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.ConnectivityManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.girls4girls.R
import com.example.girls4girls.data.CustomPreferences
import com.example.girls4girls.data.model.User
import com.example.girls4girls.databinding.FragmentVideoblogBinding
import com.example.girls4girls.presentation.MainActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat

class VideoblogFragment : Fragment() {

    private val viewModel by viewModel<VideoblogViewModel>()
    private lateinit var binding: FragmentVideoblogBinding

    private lateinit var playerParams: ViewGroup.LayoutParams

    private val args: VideoblogFragmentArgs by navArgs()
    private val videoBlog by lazy { args.currentVideoBlog}
    private val sharedPreferences by inject<CustomPreferences>()
    private val isLikedLD by lazy { MutableLiveData(videoBlog.isLiked)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoblogBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the width and height of the player
        playerParams = binding.player.layoutParams

        // Set the vertical height
        if (viewModel.defaultHeight == null){
            viewModel.defaultHeight = playerParams.height
        }

        addToWatched()

        setText()

        initPlayer()

        addFullScreenListener()

        binding.testButton.setOnClickListener {
            findNavController().navigate(R.id.action_videoblogFragment_to_quizFragment)
            binding.player.release()
        }

        binding.videoLikeButton.setOnClickListener {
            toggleLike()
        }

        // Change like icon
        isLikedLD.observe(viewLifecycleOwner){isLiked ->



            if (isLiked){
                binding.videoLikeButton.setImageResource(R.drawable.ic_heart_filled)
            } else {
                binding.videoLikeButton.setImageResource(R.drawable.ic_heart)
            }

            Log.d(TAG, "isLikedLD: ${isLiked}")
        }

    }

    private fun addToWatched() {
        viewModel.addToWatched(
            "Bearer ${sharedPreferences.fetchToken()}",
            videoBlog.id
        )
    }

    private fun toggleLike() {

        videoBlog.isLiked = !videoBlog.isLiked
        isLikedLD.value = videoBlog.isLiked

        viewModel.toggleVideoLike(
            "Bearer ${sharedPreferences.fetchToken()}",
            videoBlog.id
        )
    }

    private fun setText() {
        binding.videoTitleTxt.text = videoBlog.title

        binding.videoViewsTxt.text = videoBlog.views.toString()
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd/MM/yyyy")
        val inputDate = inputFormat.parse(videoBlog.date)
        val outputDate = outputFormat.format(inputDate)
        binding.videoDateTxt.text = outputDate
        binding.videoCategory.text = videoBlog.category?.name ?: "No Category"

        binding.descriptionTxt.text = videoBlog.description
        Glide
            .with(binding.root)
            .load(videoBlog.lecturerImage?.url ?: ERROR_IMAGE)
            .into(binding.videoSpeakerImage)
        binding.videoSpeakerName.text = videoBlog.lecturerName
        binding.videoSpeakerPosition.text = videoBlog.lecturerInfo
//
//        binding.speakerCard.setOnClickListener {
//            val action = VideoblogFragmentDirections.actionVideoblogFragmentToMentorFragment2(videoBlog.speaker)
//            findNavController().navigate(action)
//        }

    }

    private fun initPlayer() {

        // Make player observe lifecycle
        lifecycle.addObserver(binding.player)


        val listener = object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                Log.d(TAG, "Ready")

                // Set another player controller UI
                val defaultPlayerUiController = DefaultPlayerUiController(binding.player, youTubePlayer)
                binding.player.setCustomPlayerUi(defaultPlayerUiController.rootView)


                val link = videoBlog.link
                val videoID = getYoutubeVideoId(link)
                if (videoID != null){
                    youTubePlayer.loadVideo(videoID,0F)
                }
                Log.d(TAG, "videoID: ${videoID}")

            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                super.onStateChange(youTubePlayer, state)

                if (state == PlayerConstants.PlayerState.ENDED){
                    if (!videoBlog.isWatched){
                        videoBlog.isWatched = true
                    }
                    videoBlog.views += 1
                    Log.d(TAG, "onStateChange: Video is watched")
                }
            }
        }

        // Disable default controller UI
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

                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }

        })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        toggleSystemUi()

    }

    @SuppressLint("InlinedApi")
    private fun toggleSystemUi() {

        val controller =  WindowInsetsControllerCompat(requireActivity().window, binding.player)

        if (resources.configuration?.orientation == MainActivity.LANDSCAPE_SCREEN_ID){

            // Hide system UI
            (activity as AppCompatActivity).supportActionBar?.hide()
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

            // Set video player to fullscreen height
            playerParams.height = ViewGroup.LayoutParams.MATCH_PARENT

            binding.scrollView.visibility = View.GONE


        } else if (resources.configuration?.orientation == MainActivity.PORTRAIT_SCREEN_ID) {

            // Show system UI
            (activity as AppCompatActivity).supportActionBar?.show()
            controller.show(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

            // Set video player to normal height
            playerParams.height = viewModel.defaultHeight!!

            binding.scrollView.visibility = View.VISIBLE
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Destroy")
        binding.player.release()
        
    }

    fun getYoutubeVideoId(url: String): String? {
        // Extract video ID from "https://www.youtube.com/watch?v=xxxxxx" format
        val watchUrlRegex = ".*youtube\\.com\\/watch\\?v=([\\w-]+).*"
        val watchUrlMatcher = Regex(watchUrlRegex).find(url)
        if (watchUrlMatcher != null) {
            return watchUrlMatcher.groupValues[1]
        }

        // Extract video ID from "https://youtu.be/xxxxxx" format
        val shortUrlRegex = ".*youtu\\.be\\/([\\w-]+).*"
        val shortUrlMatcher = Regex(shortUrlRegex).find(url)
        if (shortUrlMatcher != null) {
            return shortUrlMatcher.groupValues[1]
        }

        // URL is not a valid YouTube video URL
        return null
    }


    companion object {
        val TAG = "Chura"
        val ERROR_IMAGE = "https://developers.google.com/static/maps/documentation/maps-static/images/error-image-generic.png"
    }

}