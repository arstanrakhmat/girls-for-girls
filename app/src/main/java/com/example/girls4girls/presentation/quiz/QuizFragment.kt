package com.example.girls4girls.presentation.quiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentQuizBinding
import com.example.girls4girls.presentation.videoblogsList.VideoblogsListFragment.Companion.TAG
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private val viewModel by viewModel<QuizViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getQuiz(6)

        viewModel.quiz.observe(viewLifecycleOwner){quiz ->
            binding.quizTitleTxt.text = quiz.title
            binding.quizQuestionsNumber.text = quiz.questions.size.toString()
            binding.quizDescription.text = quiz.description

            binding.quizStartButton.setOnClickListener {
                val action = QuizFragmentDirections.actionQuizFragmentToQuestionFragment(quiz)
                findNavController().navigate(action)
            }
        }


    }


}