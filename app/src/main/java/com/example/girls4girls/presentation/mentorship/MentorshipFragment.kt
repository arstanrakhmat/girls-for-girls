package com.example.girls4girls.presentation.mentorship

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentMentorshipBinding

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

        binding.aboutButton.setOnClickListener {
            findNavController().navigate(R.id.action_mentorshipFragment_to_aboutMentorshipFragment)
        }

        binding.mentorsButton.setOnClickListener {
            findNavController().navigate(R.id.action_mentorshipFragment_to_mentorsFragment)
        }

        binding.graduatesButton.setOnClickListener {
            findNavController().navigate(R.id.action_mentorshipFragment_to_graduatesFragment)
        }
    }

}