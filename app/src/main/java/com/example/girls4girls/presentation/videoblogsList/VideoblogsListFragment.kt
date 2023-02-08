package com.example.girls4girls.presentation.videoblogsList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.data.VideoBlog
import com.example.girls4girls.databinding.FragmentVideoblogBinding
import com.example.girls4girls.databinding.FragmentVideoblogsListBinding

class VideoblogsListFragment : Fragment() {

    private lateinit var viewModel: VideoblogsListViewModel
    private lateinit var binding: FragmentVideoblogsListBinding

    private lateinit var videoAdapter: VideoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoblogsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoAdapter = VideoAdapter()
        setAdapter()


        val videos = mutableListOf<VideoBlog>()

        for (i in 1..5){
            val newVideo = VideoBlog(i.toLong(), "Video #$i", 15)
            videos.add(newVideo)
        }
        videoAdapter.submitList(videos)
    }

    private fun setAdapter() {
        binding.listVideoblogs.adapter = videoAdapter
        videoAdapter.onVideoClickListener = {
            val action = VideoblogsListFragmentDirections.actionVideoblogsListFragmentToVideoblogFragment(it)
            findNavController().navigate(action)
        }
    }

}