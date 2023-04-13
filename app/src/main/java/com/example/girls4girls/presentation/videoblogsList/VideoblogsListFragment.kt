package com.example.girls4girls.presentation.videoblogsList

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import com.example.girls4girls.data.Category
import com.example.girls4girls.data.CustomPreferences
import com.example.girls4girls.data.VideoBlog
import com.example.girls4girls.databinding.BottomSheetCategoriesBinding
import com.example.girls4girls.databinding.FragmentVideoblogBinding
import com.example.girls4girls.databinding.FragmentVideoblogsListBinding
import com.example.girls4girls.presentation.videoblog.VideoblogFragment.Companion.TAG
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoblogsListFragment : Fragment(), SearchView.OnQueryTextListener {

    private val viewModel by viewModel<VideoblogsListViewModel>()
    private lateinit var binding: FragmentVideoblogsListBinding
    private val sharedPreferences by inject<CustomPreferences>()

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

        loadVideos()
        
        binding.searchView.setOnQueryTextListener(this)

        binding.categoryButton.setOnClickListener { view ->
            showBottomSheet()
        }

        setTabLayout()

    }

    private fun loadVideos() {

        binding.videosListProgressBar.visibility = View.VISIBLE

        viewModel.getVideos()
        viewModel._videosList.observe(requireActivity()){
            videoAdapter.modifyList(it)
            binding.videosListProgressBar.visibility = View.GONE
        }
    }

    private fun setTabLayout() {
        binding.videosTabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val chosenTypeVideosList = when (tab?.position){
                    0 -> viewModel._videosList
                    1 -> {
                        viewModel.getLikedVideos("Bearer ${sharedPreferences.fetchToken()}")
                        viewModel._likedVideosList
                    }
                    else -> {viewModel._videosList}
                }

                chosenTypeVideosList.observe(requireActivity()){videos ->
                    videoAdapter.submitList(videos)
                    Log.d(TAG, "chosenTypeVideosList: ${videos}")
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
        dialog.setCancelable(true)
        dialog.setContentView(binding.root)

        viewModel.getCategories()

        val categoryAdapter = CategoryAdapter()
        binding.categoryList.adapter = categoryAdapter
        viewModel._categories.observe(requireActivity()){categories ->
            categoryAdapter.submitList(categories)
            Log.d(TAG, "showBottomSheet: ${categories}")
        }

        categoryAdapter.onCategoryClickListener = {category->
            viewModel._videosList.observe(requireActivity()){videosList ->

                videoAdapter.modifyList(videosList.filter { videoBlog ->
                    videoBlog.category.name == category.name
                })
            }


            dialog.dismiss()
        }


//        binding.categoryAll.setOnClickListener {
//            videoAdapter.modifyList(viewModel._videosList)
//            dialog.dismiss()
//        }
//
//        for (category in categoryList){
//            category.setOnClickListener {
//                videoAdapter.modifyList(viewModel._videosList.filter { videoBlog ->
//                    videoBlog.category == category.text
//                })
//                dialog.dismiss()
//            }
//        }

        dialog.show()
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