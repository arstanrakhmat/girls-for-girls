package com.example.girls4girls.presentation.welcome

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentWelcomeBinding
import com.example.girls4girls.presentation.MainActivity
import com.example.girls4girls.presentation.auth.LoginActivity

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    private var titlesList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()
        postToList()

//        binding.log.setOnClickListener {
//            startActivity(Intent(requireContext(), LoginActivity::class.java))
//        }
//
//        binding.no.setOnClickListener {
//            startActivity(Intent(requireContext(), MainActivity::class.java))
//            activity?.finish()
//        }

        binding.signInWelcomePage.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }

        binding.enterAsGuestWelcomePage.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

    }

    private fun addToList(title: String, image: Int) {
        titlesList.add(title)
        imagesList.add(image)
    }

    private fun postToList() {
        addToList(
            resources.getString(R.string.bottom_nav_mentorship),
            R.drawable.welcome_page_111
        )
        addToList(
            resources.getString(R.string.trainings_and_forums),
            R.drawable.onboarding
        )
        addToList(
            resources.getString(R.string.useful_video_lessons),
            R.drawable.onboarding2
        )
    }

    private fun setupViewPager() {
        val viewPager2 = binding.viewPager2WelcomePage
        viewPager2.adapter = ViewPagerAdapterWelcome(titlesList, imagesList)

        val dotsIndicator = binding.indicatorWelcomePage
        dotsIndicator.attachTo(viewPager2)

    }

}