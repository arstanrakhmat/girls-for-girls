package com.example.girls4girls.presentation.mentorship.mentor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.databinding.FragmentMentorsBinding
import com.example.girls4girls.presentation.mentorship.MentorshipFragmentDirections
import com.example.girls4girls.presentation.mentorship.MentorshipViewModel

class MentorsListFragment : Fragment() {

    private val viewModel by viewModels<MentorshipViewModel>()

    private lateinit var binding: FragmentMentorsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMentorsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mentorshipAdapter = MentorsAdapter()
        binding.mentorsList.adapter = mentorshipAdapter

        mentorshipAdapter.onMentorClickListener = {mentor ->
            val action = MentorshipFragmentDirections.actionMentorshipFragmentToMentorFragment(mentor)
            findNavController().navigate(action)
        }
    }
}