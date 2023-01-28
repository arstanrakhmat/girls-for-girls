package com.example.girls4girls.presentation.videoblogsList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentVideoblogBinding
import com.example.girls4girls.databinding.FragmentVideoblogsListBinding

class VideoblogsListFragment : Fragment() {

    private lateinit var viewModel: VideoblogsListViewModel
    private lateinit var binding: FragmentVideoblogsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoblogsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.readVideoBlogButton.setOnClickListener {
            findNavController().navigate(R.id.action_videoblogsListFragment_to_videoblogFragment)
        }
    }

}