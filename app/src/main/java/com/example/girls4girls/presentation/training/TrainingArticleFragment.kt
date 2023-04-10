package com.example.girls4girls.presentation.training

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentTrainingArticleBinding
import com.example.girls4girls.utils.toFormattedDate

class TrainingArticleFragment : Fragment() {

    private lateinit var binding: FragmentTrainingArticleBinding
    private val args: TrainingArticleFragmentArgs by navArgs()
    private lateinit var speakerAdapter: SpeakerAdapter

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
        setupRecyclerView()
    }

    private fun clickListeners() {
        binding.btnApply.setOnClickListener {
            findNavController().navigate(R.id.action_trainingArticleFragment_to_trainingApplyRequirementsFragment)
        }
    }

    private fun setupRecyclerView() {
        speakerAdapter = SpeakerAdapter()
        binding.rvSpeaker.apply {
            adapter = speakerAdapter
        }
    }

    private fun setupTrainingArticle() {
        val training = args.training
        Glide.with(this).load(training.images[0].url).into(binding.img)
        binding.title.text = training.title
        binding.date.text = training.eventDate.toFormattedDate()
        binding.time.text = training.time
        binding.location.text = training.location
        binding.deadline.text = training.deadlineDate.toFormattedDate()
        binding.description.text = training.description
    }

}