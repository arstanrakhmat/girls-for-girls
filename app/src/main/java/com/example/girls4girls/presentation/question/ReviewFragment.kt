package com.example.girls4girls.presentation.question

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.girls4girls.R
import com.example.girls4girls.data.Answer
import com.example.girls4girls.databinding.FragmentQuestionBinding
import com.example.girls4girls.databinding.FragmentReviewBinding

class ReviewFragment : Fragment() {

    private lateinit var binding: FragmentReviewBinding
    private lateinit var answerAdapter: AnswerAdapter
    private val args by navArgs<ReviewFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        answerAdapter = AnswerAdapter()

        binding.answersList.adapter = answerAdapter
        answerAdapter.submitList(args.answers.toList())

    }
}