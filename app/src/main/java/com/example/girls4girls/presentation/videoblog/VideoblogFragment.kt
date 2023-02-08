package com.example.girls4girls.presentation.videoblog

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentVideoblogBinding
import com.example.girls4girls.presentation.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class VideoblogFragment : Fragment() {

    private lateinit var viewModel: VideoblogViewModel
    private lateinit var binding: FragmentVideoblogBinding

    private val args by navArgs<VideoblogFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoblogBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.VideoBlogText.text = args.currentVideoBlog.title

    }

}