package com.example.girls4girls.presentation.training

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentTrainingApplyFirstPageBinding

class TrainingApplyFirstPageFragment : Fragment() {

    private lateinit var binding: FragmentTrainingApplyFirstPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTrainingApplyFirstPageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListeners()
    }

    private fun clickListeners() {
        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_trainingApplyFirstPageFragment_to_trainingApplySecondPageFragment)
        }
    }

}