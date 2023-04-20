package com.example.girls4girls.presentation.forum

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.girls4girls.databinding.FragmentPastForumArticleBinding
import com.example.girls4girls.presentation.training.SpeakerAdapter
import com.example.girls4girls.utils.toFormattedDate
import org.koin.androidx.viewmodel.ext.android.viewModel

class PastForumArticleFragment : Fragment() {

    private lateinit var binding: FragmentPastForumArticleBinding
    private val args: PastForumArticleFragmentArgs by navArgs()
    private lateinit var speakerAdapter: SpeakerAdapter
    private val forumViewModel by viewModel<ForumViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPastForumArticleBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        forumViewModel.getForumById(args.forum.id)
        setupRecyclerView()

    }

    private fun setupObservers() {
        forumViewModel.forumById.observe(requireActivity()) {
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

        forumViewModel.error.observe(requireActivity()) {
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