package com.example.girls4girls.presentation.forum

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentForumBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForumFragment : Fragment() {

    private lateinit var binding: FragmentForumBinding
    private lateinit var forumAdapter: ForumAdapter
    private lateinit var upcomingForumAdapter: UpcomingForumAdapter
    private val forumVieModel by viewModel<ForumViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForumBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        clickListeners()
        forumVieModel.getUpcomingForums(1, 6, "ASC", "id")
        forumVieModel.getPastForums(1, 3, "ASC", "id")
    }

    private fun setupObservers() {
        forumVieModel.upcomingForum.observe(requireActivity()) {
            upcomingForumAdapter.differ.submitList(it.data)
            binding.progressBar.visibility = View.GONE
        }

        forumVieModel.pastForum.observe(requireActivity()) {
            forumAdapter.differ.submitList(it.data)
        }

        forumVieModel.error.observe(requireActivity()) {
            Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun clickListeners() {
        binding.btnShowPastForums.setOnClickListener {
            findNavController().navigate(R.id.action_trainingsListFragment_to_pastForumsListFragment)
        }

        forumAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("forum", it)
            }
            findNavController().navigate(
                R.id.action_trainingsListFragment_to_pastForumArticleFragment,
                bundle
            )
        }

        upcomingForumAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("forum", it)
            }
            findNavController().navigate(
                R.id.action_trainingsListFragment_to_forumArticleFragment,
                bundle
            )
        }

    }

    private fun setupRecyclerView() {
        upcomingForumAdapter = UpcomingForumAdapter()
        binding.rvUpcomingForums.apply {
            adapter = upcomingForumAdapter
        }

        forumAdapter = ForumAdapter()
        binding.rvPastForums.apply {
            adapter = forumAdapter
        }
    }

}