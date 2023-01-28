package com.example.girls4girls.presentation.trainingsList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentTrainingsListBinding

class TrainingsListFragment : Fragment() {

    private lateinit var viewModel: TrainingsListViewModel
    private lateinit var binding: FragmentTrainingsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrainingsListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.readTrainingButton.setOnClickListener {
            findNavController().navigate(R.id.action_trainingsListFragment_to_trainingFragment)
        }
    }
}