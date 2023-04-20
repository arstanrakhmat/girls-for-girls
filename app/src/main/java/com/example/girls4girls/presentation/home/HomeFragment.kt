package com.example.girls4girls.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.girls4girls.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel by viewModel<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setImagePager()

        setEventRecycler()

    }

    private fun setImagePager() {
        binding.mainTeam.adapter = ImagePagerAdapter()
        binding.mainTeamDotsIndicator.attachTo(binding.mainTeam)
    }

    private fun setEventRecycler() {
        val eventAdapter = EventAdapter()
        binding.eventRecyclerView.adapter = eventAdapter

        viewModel.getUpcomingEvents()
        viewModel._upcomingEvents.observe(viewLifecycleOwner){eventsList ->
            eventAdapter.submitList(eventsList)
        }
    }
}