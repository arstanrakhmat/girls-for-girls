package com.example.girls4girls.presentation.videoblogsList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import androidx.appcompat.widget.SearchView
import com.example.girls4girls.data.VideoBlog
import com.example.girls4girls.databinding.FragmentVideoblogBinding
import com.example.girls4girls.databinding.FragmentVideoblogsListBinding

class VideoblogsListFragment : Fragment(), SearchView.OnQueryTextListener {

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

        setAdapter()
        setSearchView()

        val videos = mutableListOf<VideoBlog>()

        for (i in 1..5){
            val newVideo = VideoBlog(i.toLong(), "Video #$i", 15)
            videos.add(newVideo)
        }
        videoAdapter.modifyList(videos)
    }

    private fun setSearchView() {
        binding.searchView.setOnQueryTextListener(this)
    }

    private fun setAdapter() {
        videoAdapter = VideoAdapter()
        binding.listVideoblogs.adapter = videoAdapter
        videoAdapter.onVideoClickListener = {
            val action = VideoblogsListFragmentDirections.actionVideoblogsListFragmentToVideoblogFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String): Boolean {
        videoAdapter.filter(query)
        return true
    }

    companion object{
        val TAG = "Chura"
    }

}