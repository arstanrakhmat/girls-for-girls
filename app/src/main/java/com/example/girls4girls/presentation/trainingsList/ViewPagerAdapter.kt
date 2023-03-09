package com.example.girls4girls.presentation.trainingsList

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.girls4girls.presentation.forum.ForumFragment
import com.example.girls4girls.presentation.training.TrainingFragment

private const val NUM_OF_FRAGMENTS = 2

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = NUM_OF_FRAGMENTS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TrainingFragment()
            1 -> ForumFragment()
            else -> {
                Fragment()
            }
        }
    }
}