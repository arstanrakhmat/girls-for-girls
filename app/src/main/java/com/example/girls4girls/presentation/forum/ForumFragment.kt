package com.example.girls4girls.presentation.forum

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentForumBinding
import com.example.girls4girls.databinding.FragmentForumsListBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ForumFragment : Fragment() {

    private lateinit var viewModel: ForumViewModel
    private lateinit var binding: FragmentForumBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForumBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}