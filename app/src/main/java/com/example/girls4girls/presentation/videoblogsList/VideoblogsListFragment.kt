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
import co.mobiwise.materialintro.shape.Focus
import co.mobiwise.materialintro.shape.FocusGravity
import co.mobiwise.materialintro.shape.ShapeType
import co.mobiwise.materialintro.view.MaterialIntroView
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

//        setIntroViews()

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


    private fun setIntroViews() {
        val intro3 = MaterialIntroView.Builder(requireActivity())
            .enableDotAnimation(true)
            .enableIcon(false)
            .setFocusGravity(FocusGravity.CENTER)
            .setFocusType(Focus.MINIMUM)
            .setDelayMillis(100)
            .enableFadeAnimation(true)
            .performClick(true)
            .setInfoText("Список видео")
            .setShape(ShapeType.CIRCLE)
            .setTarget(binding.listVideoblogs)
            .setUsageId("intro33333") //THIS SHOULD BE UNIQUE ID

        val intro2 = MaterialIntroView.Builder(requireActivity())
            .enableDotAnimation(true)
            .enableIcon(false)
            .setFocusGravity(FocusGravity.CENTER)
            .setFocusType(Focus.MINIMUM)
            .setDelayMillis(100)
            .enableFadeAnimation(true)
            .performClick(true)
            .setInfoText("Сортировка по категориям")
            .setShape(ShapeType.CIRCLE)
            .setTarget(binding.categoryButton)
            .setUsageId("intro22222") //THIS SHOULD BE UNIQUE ID
            .setListener{ s -> intro3.show()}

        MaterialIntroView.Builder(requireActivity())
            .enableDotAnimation(true)
            .enableIcon(false)
            .setFocusGravity(FocusGravity.CENTER)
            .setFocusType(Focus.MINIMUM)
            .setDelayMillis(500)
            .enableFadeAnimation(true)
            .performClick(true)
            .setInfoText("Вы можете искать нужные вам видео")
            .setShape(ShapeType.CIRCLE)
            .setTarget(binding.searchView)
            .setUsageId("intro11111") //THIS SHOULD BE UNIQUE ID
            .setListener{ s -> intro2.show()}
            .show()
    }

    private fun loadVideos() {

        binding.videosListProgressBar.visibility = View.VISIBLE

        viewModel.getVideos()
        viewModel._videosList.observe(viewLifecycleOwner){
            viewModel.getLikedVideos("Bearer ${sharedPreferences.fetchToken()}")
            for (videoBlog in it) {
                viewModel._likedVideosList.observe(viewLifecycleOwner){ likedVideos ->
                    Log.d(TAG, "likedVideos: ${likedVideos}")
                    val likedVideosIds = likedVideos.map { it.id }.toHashSet()
                    videoBlog.isLiked = likedVideosIds.contains(videoBlog.id)
                }

            }

            Log.d(TAG, "Videos: ${it}")

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
                    else -> {
                        viewModel.getWatchedVideos("Bearer ${sharedPreferences.fetchToken()}")
                        viewModel._watchedVideosList
                    }
                }

                chosenTypeVideosList.observe(viewLifecycleOwner ){videos ->
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
        viewModel._categories.observe(viewLifecycleOwner){categories ->
            categoryAdapter.submitList(categories)
            Log.d(TAG, "showBottomSheet: ${categories}")
        }

        categoryAdapter.onCategoryClickListener = {category->
            viewModel._videosList.observe(viewLifecycleOwner){videosList ->

//                videoAdapter.modifyList(videosList.filter { videoBlog ->
//                    videoBlog.category.name == category.name
//                })
            }


            dialog.dismiss()
        }

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