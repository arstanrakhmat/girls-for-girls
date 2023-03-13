package com.example.girls4girls.presentation.mentorship

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.girls4girls.databinding.FragmentMentorshipBinding
import com.google.android.material.tabs.TabLayoutMediator

class MentorshipFragment : Fragment() {

    private lateinit var binding: FragmentMentorshipBinding
    private lateinit var viewModel: MentorshipViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMentorshipBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mentorshipViewPager.adapter = MentorshipViewPagerAdapter(requireActivity())
        val fragmentList = listOf("О программе","Менторы", "Выпускники")
        TabLayoutMediator(binding.mentorshipTabLayout, binding.mentorshipViewPager){tab, pos ->
            tab.text = fragmentList[pos]
        }.attach()
    }
}