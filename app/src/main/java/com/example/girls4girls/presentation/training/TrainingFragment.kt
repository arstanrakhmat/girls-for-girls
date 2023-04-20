package com.example.girls4girls.presentation.training

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentTrainingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrainingFragment : Fragment() {

    private lateinit var binding: FragmentTrainingBinding
    private lateinit var trainingAdapter: TrainingAdapter
    private lateinit var upcomingTrainingAdapter: UpcomingTrainingAdapter
    private val trainingViewModel by viewModel<TrainingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUpcomingTrainings()
        setupRecyclerView()
        clickListeners()
        trainingViewModel.getUpcomingTrainings(1, 6, "ASC", "id")
        trainingViewModel.getPastTrainings(1, 3, "ASC", "id")
    }

    private fun initUpcomingTrainings() {
        trainingViewModel.upcomingTraining.observe(requireActivity()) {
            upcomingTrainingAdapter.differ.submitList(it.data)
            binding.progressBar.visibility = View.GONE
        }

        trainingViewModel.pastTraining.observe(requireActivity()) {
            trainingAdapter.differ.submitList(it.data)
        }

        trainingViewModel.error.observe(requireActivity()) {
            Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun setupRecyclerView() {
        trainingAdapter = TrainingAdapter()
        binding.rvPastTrainings.apply {
            adapter = trainingAdapter
        }


        upcomingTrainingAdapter = UpcomingTrainingAdapter()
        binding.rvUpcomingTrainings.apply {
            adapter = upcomingTrainingAdapter
        }
    }

    private fun clickListeners() {
        binding.btnShowPastTrainings.setOnClickListener {
            findNavController().navigate(R.id.action_trainingsListFragment_to_pastTrainingsListFragment)
        }

        upcomingTrainingAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("training", it)
            }
            findNavController().navigate(
                R.id.action_trainingsListFragment_to_trainingArticleFragment,
                bundle
            )
        }

        trainingAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("training", it)
            }

            findNavController().navigate(
                R.id.action_trainingsListFragment_to_pastTrainingArticleFragment,
                bundle
            )
        }

    }

}