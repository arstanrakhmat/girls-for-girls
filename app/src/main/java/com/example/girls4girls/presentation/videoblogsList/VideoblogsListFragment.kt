package com.example.girls4girls.presentation.videoblogsList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.girls4girls.data.VideoBlog
import com.example.girls4girls.databinding.FragmentVideoblogBinding
import com.example.girls4girls.databinding.FragmentVideoblogsListBinding

class VideoblogsListFragment : Fragment(), SearchView.OnQueryTextListener {

    private val viewModel: VideoblogsListViewModel by viewModels()
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
        
        binding.searchView.setOnQueryTextListener(this)

        binding.categoryButton.setOnClickListener { view ->
            showPopUpMenu(view)
        }
        
        videoAdapter.modifyList(viewModel.videoList)
    }

    private fun showPopUpMenu(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)

        popupMenu.inflate(R.menu.category_popup_menu)

        popupMenu.setOnMenuItemClickListener { category ->
            when(category.itemId){
                R.id.all -> {
                    videoAdapter.modifyList(viewModel.videoList)
                    binding.categoryTxt.text = category.title
                    true
                }
                R.id.category1 -> {
                    videoAdapter.modifyList(viewModel.videoList.filter { videoBlog ->
                        videoBlog.category == category.title
                    })
                    binding.categoryTxt.text = category.title
                    true
                }
                R.id.category2 -> {
                    videoAdapter.modifyList(viewModel.videoList.filter { videoBlog ->
                        videoBlog.category == category.title
                    })
                    binding.categoryTxt.text = category.title
                    true
                }
                R.id.category3 -> {
                    videoAdapter.modifyList(viewModel.videoList.filter { videoBlog ->
                        videoBlog.category == category.title
                    })
                    binding.categoryTxt.text = category.title
                    true
                }
                R.id.category4 -> {
                    videoAdapter.modifyList(viewModel.videoList.filter { videoBlog ->
                        videoBlog.category == category.title
                    })
                    binding.categoryTxt.text = category.title
                    true
                }
                R.id.category5 -> {
                    videoAdapter.modifyList(viewModel.videoList.filter { videoBlog ->
                        videoBlog.category == category.title
                    })
                    binding.categoryTxt.text = category.title
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    private fun setAdapter() {
        videoAdapter = VideoAdapter()
        binding.listVideoblogs.adapter = videoAdapter
        
        videoAdapter.onVideoClickListener = {videoBlog ->

            val action = VideoblogsListFragmentDirections.
                            actionVideoblogsListFragmentToVideoblogFragment(videoBlog)
            
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