package com.example.girls4girls.presentation.forumsList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentForumsListBinding

class ForumsListFragment : Fragment() {

    private lateinit var binding: FragmentForumsListBinding
    private lateinit var viewModel: ForumsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForumsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.readForumButton.setOnClickListener {
//            findNavController().navigate(R.id.action_forumsListFragment_to_forumFragment)
//        }
    }

}