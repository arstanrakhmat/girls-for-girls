package com.example.girls4girls.presentation.training

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.girls4girls.databinding.FragmentPastTrainingArticleBinding
import com.example.girls4girls.utils.toFormattedDate
import org.koin.androidx.viewmodel.ext.android.viewModel

class PastTrainingArticleFragment : Fragment() {

    private lateinit var binding: FragmentPastTrainingArticleBinding
    private val args: PastTrainingArticleFragmentArgs by navArgs()
    private lateinit var speakerAdapter: SpeakerAdapter
    private val trainingViewModel by viewModel<TrainingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPastTrainingArticleBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        trainingViewModel.getTrainingById(args.training.id)
        setupObservers()
    }

    private fun setupObservers() {
        trainingViewModel.trainingById.observe(requireActivity()) {
            if (it.images != null) {
                Glide.with(this).load(it.images[0].url).into(binding.img)
            }
            with(binding) {
                title.text = it.title
                date.text = it.eventDate.toFormattedDate()
                time.text = it.time
                location.text = it.location
                deadline.text = it.deadlineDate.toFormattedDate()
                description.text = it.description
            }
            hideProgressBar()
        }

        trainingViewModel.error.observe(requireActivity()) {
            Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_LONG)
                .show()
            Log.d("training", it.toString())
            hideProgressBar()
        }
    }

    private fun setupRecyclerView() {
        speakerAdapter = SpeakerAdapter()
        binding.rvSpeaker.apply {
            adapter = speakerAdapter
        }
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

}