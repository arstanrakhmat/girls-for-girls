package com.example.girls4girls.presentation.quiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentQuizBinding
import com.example.girls4girls.presentation.videoblogsList.VideoblogsListFragment.Companion.TAG

class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private val viewmodel by viewModels<QuizViewModel>()
    private var questionNumber = 0
    private lateinit var shuffledAnswers: MutableList<String>
    private val correctAnswers = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setQuestion()

        binding.nextButton.setOnClickListener {
            val checkId = binding.radioButtonGroup.checkedRadioButtonId

            // Remember the chosen button
            var answerIndex = 0
            when (checkId){
                R.id.answer2button -> answerIndex = 1
                R.id.answer3button -> answerIndex = 2
                R.id.answer4button -> answerIndex = 3
            }


            if (viewmodel.questions[questionNumber].answers[0] != shuffledAnswers[answerIndex]){
                Toast.makeText(requireContext(), "Wrong answer", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Log.d(TAG, "questionNumber: ${questionNumber}")
            Log.d(TAG, "questions.size: ${viewmodel.questions.size}")

            if (questionNumber + 1 == viewmodel.questions.size){
                Toast.makeText(requireContext(), "Congratulations!!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            questionNumber += 1
            setQuestion()

        }
    }

    private fun setQuestion() {
        binding.questionNumber.text = "Задание ${questionNumber + 1}"

        shuffledAnswers = viewmodel.questions[questionNumber].answers.toMutableList()
        shuffledAnswers.shuffle()

        binding.questionText.text = viewmodel.questions[questionNumber].text

        binding.answer1button.text = shuffledAnswers[0]
        binding.answer2button.text = shuffledAnswers[1]
        binding.answer3button.text = shuffledAnswers[2]
        binding.answer4button.text = shuffledAnswers[3]
    }
}