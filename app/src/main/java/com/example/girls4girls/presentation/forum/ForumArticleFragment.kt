package com.example.girls4girls.presentation.forum

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.girls4girls.databinding.FragmentForumArticleBinding
import com.example.girls4girls.presentation.training.SpeakerAdapter

class ForumArticleFragment : Fragment() {

    private lateinit var binding: FragmentForumArticleBinding
    private lateinit var speakerAdapter: SpeakerAdapter
    private val args: ForumArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentForumArticleBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupTrainingArticle()
    }

    private fun setupRecyclerView() {
        speakerAdapter = SpeakerAdapter()
        binding.rvSpeaker.apply {
            adapter = speakerAdapter
        }
    }

    private fun setupTrainingArticle() {
        val training = args.forum
        binding.img.setImageResource(training.image)
        binding.title.text = training.title
        binding.date.text = training.date
        binding.time.text = training.time
        binding.location.text = training.location
        binding.deadline.text = training.deadline
        binding.description.text = training.description
    }

}