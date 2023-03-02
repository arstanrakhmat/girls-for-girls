package com.example.girls4girls.presentation.mentorship

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.girls4girls.databinding.FragmentMentorsBinding

class MentorsFragment : Fragment() {

    private lateinit var binding: FragmentMentorsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMentorsBinding.inflate(inflater, container, false)
        return binding.root
    }
}