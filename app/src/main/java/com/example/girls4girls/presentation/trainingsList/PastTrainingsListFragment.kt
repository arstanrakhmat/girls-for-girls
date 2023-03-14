package com.example.girls4girls.presentation.trainingsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentPastTrainingsListBinding

class PastTrainingsListFragment : Fragment() {

    private lateinit var binding: FragmentPastTrainingsListBinding
    private lateinit var pastTrainingsListAdapter: PastTrainingsListAdapter

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
        setupRecyclerView()
        clickListeners()
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
                R.id.action_pastTrainingsListFragment_to_trainingArticleFragment,
                bundle
            )
        }
    }

}