package com.example.girls4girls.presentation.mentorship

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AboutMentorshipFragment()
            1 -> MentorsFragment()
            else -> GraduatesFragment()
        }
    }
}