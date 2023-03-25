package com.example.girls4girls.presentation.forum

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentPastForumsListBinding

class PastForumsListFragment : Fragment() {

    private lateinit var binding: FragmentPastForumsListBinding
    private lateinit var pastForumListAdapter: PastForumListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPastForumsListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        clickListeners()
    }

    private fun setupRecyclerView() {
        pastForumListAdapter = PastForumListAdapter()
        binding.rvPastForumsList.apply {
            adapter = pastForumListAdapter
        }
    }

    private fun clickListeners() {
        pastForumListAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("forum", it)
            }
            findNavController().navigate(
                R.id.action_pastForumsListFragment_to_forumArticleFragment,
                bundle
            )
        }
    }


}