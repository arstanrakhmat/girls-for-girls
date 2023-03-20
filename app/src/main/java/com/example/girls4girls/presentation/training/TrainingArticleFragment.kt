package com.example.girls4girls.presentation.training

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentTrainingArticleBinding

class TrainingArticleFragment : Fragment() {

    private lateinit var binding: FragmentTrainingArticleBinding
    private val args: TrainingArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentTrainingArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTrainingArticle()
        clickListeners()
    }

    private fun clickListeners() {
        binding.btnApply.setOnClickListener {
            findNavController().navigate(R.id.action_trainingArticleFragment_to_trainingApplyRequirementsFragment)
        }
    }

    private fun setupTrainingArticle() {
        val training = args.training
        binding.img.setImageResource(training.image)
        binding.title.text = training.title
        binding.date.text = training.date
        binding.time.text = training.time
        binding.location.text = training.location
        binding.deadline.text = training.deadline
        binding.description.text = training.description
    }

}