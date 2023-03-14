package com.example.girls4girls.presentation.training

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentTrainingBinding

class TrainingFragment : Fragment() {

    private lateinit var binding: FragmentTrainingBinding
    private lateinit var trainingAdapter: TrainingAdapter
    private lateinit var upcomingTrainingAdapter: UpcomingTrainingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        clickListeners()
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

        trainingAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("training", it)
            }
            findNavController().navigate(
                R.id.action_trainingsListFragment_to_trainingArticleFragment,
                bundle
            )
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
    }

}