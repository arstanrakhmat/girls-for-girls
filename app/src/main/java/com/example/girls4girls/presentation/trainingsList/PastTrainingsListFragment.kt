package com.example.girls4girls.presentation.trainingsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentPastTrainingsListBinding
import com.example.girls4girls.presentation.training.TrainingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PastTrainingsListFragment : Fragment() {

    private lateinit var binding: FragmentPastTrainingsListBinding
    private lateinit var pastTrainingsListAdapter: PastTrainingsListAdapter
    private val trainingViewModel by viewModel<TrainingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPastTrainingsListBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUpcomingTrainings()
        setupRecyclerView()
        clickListeners()
        trainingViewModel.getPastTrainings(1, 10, "ASC", "id")
    }

    private fun initUpcomingTrainings() {

        trainingViewModel.pastTraining.observe(requireActivity()) {
            pastTrainingsListAdapter.differ.submitList(it.data)
        }

        trainingViewModel.error.observe(requireActivity()) {
            Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun setupRecyclerView() {
        pastTrainingsListAdapter = PastTrainingsListAdapter()
        binding.rvPastTrainingsList.apply {
            adapter = pastTrainingsListAdapter
        }
    }

    private fun clickListeners() {
        pastTrainingsListAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("training", it)
            }
            findNavController().navigate(
                R.id.action_pastTrainingsListFragment_to_pastTrainingArticleFragment,
                bundle
            )
        }
    }

}