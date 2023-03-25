package com.example.girls4girls.presentation.videoblogsList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import com.example.girls4girls.data.VideoBlog
import com.example.girls4girls.databinding.BottomSheetCategoriesBinding
import com.example.girls4girls.databinding.FragmentVideoblogBinding
import com.example.girls4girls.databinding.FragmentVideoblogsListBinding
import com.example.girls4girls.presentation.videoblog.VideoblogFragment.Companion.TAG
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout

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
//            showPopUpMenu(view)
            showBottomSheet()
        }
        
        videoAdapter.modifyList(viewModel.videoList)

        binding.videosTabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position){
                    0 -> videoAdapter.modifyList(viewModel.videoList)
                    1 -> videoAdapter.modifyList(viewModel.videoList.filter { videoBlog ->
                        videoBlog.isLiked
                    })
                    2 -> videoAdapter.modifyList(viewModel.videoList.filter { videoBlog ->
                        videoBlog.isWatched
                    })
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

    }

    private fun showBottomSheet() {
        val binding = BottomSheetCategoriesBinding.inflate(LayoutInflater.from(context))
        val dialog = BottomSheetDialog(requireContext())
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)


        val categoryList = listOf(binding.category1,
            binding.category2,
            binding.category3,
            binding.category4,
            binding.category5)

        binding.categoryAll.setOnClickListener {
            videoAdapter.modifyList(viewModel.videoList)
            dialog.dismiss()
        }

        for (category in categoryList){
            category.setOnClickListener {
                videoAdapter.modifyList(viewModel.videoList.filter { videoBlog ->
                    videoBlog.category == category.text
                })
                dialog.dismiss()
            }
        }

        binding.closeBottomSheetButton.setOnClickListener {
            dialog.dismiss()
        }


        dialog.show()
    }

    private fun showPopUpMenu(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)

        popupMenu.inflate(R.menu.category_popup_menu)

        popupMenu.setOnMenuItemClickListener { category ->
            when(category.itemId){
                R.id.category1,
                R.id.category2,
                R.id.category3,
                R.id.category4,
                R.id.category5 -> {
                    videoAdapter.modifyList(viewModel.videoList.filter { videoBlog ->
                        videoBlog.category == category.title
                    })
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