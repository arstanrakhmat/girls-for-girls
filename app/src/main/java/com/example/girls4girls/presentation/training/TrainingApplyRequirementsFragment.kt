package com.example.girls4girls.presentation.training

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentTrainingApplyRequirementsBinding

class TrainingApplyRequirementsFragment : Fragment() {

    private lateinit var binding: FragmentTrainingApplyRequirementsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTrainingApplyRequirementsBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListeners()
    }

    private fun clickListeners() {
        binding.applyBtn.setOnClickListener {
            findNavController().navigate(R.id.action_trainingApplyRequirementsFragment_to_trainingApplyFirstPageFragment)
        }
    }

}