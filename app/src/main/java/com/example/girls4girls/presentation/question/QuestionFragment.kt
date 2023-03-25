package com.example.girls4girls.presentation.question

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
import com.example.girls4girls.databinding.FragmentQuestionBinding
import com.example.girls4girls.databinding.FragmentQuizBinding
import com.example.girls4girls.presentation.videoblogsList.VideoblogsListFragment


class QuestionFragment : Fragment() {

    private lateinit var binding: FragmentQuestionBinding
    private val viewmodel by viewModels<QuestionViewModel>()
    private var questionNumber = 0
    private lateinit var shuffledAnswers: MutableList<String>
    private val answers: MutableList<Boolean> = mutableListOf()
    private var correctAnswers = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setQuestion()

        binding.prevButton.setOnClickListener {

            questionNumber -= 1
            answers.dropLast(1)
            Log.d(VideoblogsListFragment.TAG, "questionNumber: ${questionNumber}")
            Log.d(VideoblogsListFragment.TAG, "questions.size: ${viewmodel.questions.size}")



            setQuestion()
        }

        binding.nextButton.setOnClickListener {


            // Remember the chosen button
            val checkId = binding.radioButtonGroup.checkedRadioButtonId

            var answerIndex = 0
            when (checkId){
                R.id.answer2button -> answerIndex = 1
                R.id.answer3button -> answerIndex = 2
                R.id.answer4button -> answerIndex = 3
            }

            if (viewmodel.questions[questionNumber].answers[0] != shuffledAnswers[answerIndex]){
                answers.add(false)
            } else {
                answers.add(true)
            }



            if (questionNumber + 1 == viewmodel.questions.size){
//                Toast.makeText(requireContext(),
//                    "Congratulations!! You made $correctAnswers out of ${viewmodel.questions.size}",
//                    Toast.LENGTH_SHORT).show()
                val action = QuestionFragmentDirections.actionQuestionFragmentToResultFragment(
                    answers.toBooleanArray()
                )
                findNavController().navigate(action)

                return@setOnClickListener
            }
            questionNumber += 1

            Log.d(VideoblogsListFragment.TAG, "questionNumber: ${questionNumber}")
            Log.d(VideoblogsListFragment.TAG, "questions.size: ${viewmodel.questions.size}")

            setQuestion()



        }
    }

    private fun setQuestion() {

        if (questionNumber == 0){
            binding.prevButton.visibility = View.INVISIBLE
        } else {
            binding.prevButton.visibility = View.VISIBLE
        }

        if (questionNumber == viewmodel.questions.size - 1){
            binding.nextButton.text = "Завершить"
        } else {
            binding.nextButton.text = "Далее"
        }

        binding.questionNumber.text = "${questionNumber + 1}."

        shuffledAnswers = viewmodel.questions[questionNumber].answers.toMutableList()
        shuffledAnswers.shuffle()

        binding.questionText.text = viewmodel.questions[questionNumber].text

        binding.answer1button.text = shuffledAnswers[0]
        binding.answer2button.text = shuffledAnswers[1]
        binding.answer3button.text = shuffledAnswers[2]
        binding.answer4button.text = shuffledAnswers[3]
    }
}