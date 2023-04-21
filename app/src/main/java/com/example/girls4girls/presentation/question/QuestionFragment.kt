package com.example.girls4girls.presentation.question

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.girls4girls.R
import com.example.girls4girls.data.Answer
import com.example.girls4girls.data.Option
import com.example.girls4girls.data.Question
import com.example.girls4girls.databinding.FragmentQuestionBinding
import com.example.girls4girls.databinding.FragmentQuizBinding
import com.example.girls4girls.presentation.quiz.QuizFragment
import com.example.girls4girls.presentation.videoblog.VideoblogFragment.Companion.TAG
import com.example.girls4girls.presentation.videoblogsList.VideoblogsListFragment
import kotlinx.parcelize.Parcelize


class QuestionFragment : Fragment() {

    private lateinit var binding: FragmentQuestionBinding
//    private val viewmodel by viewModels<QuestionViewModel>()
    private var questionNumber = 0
    private lateinit var shuffledAnswers: MutableList<Option>
    private val answers: MutableList<Answer> = mutableListOf()

    private val quizArgs by navArgs<QuestionFragmentArgs>()
    private val quiz by lazy { quizArgs.quiz }

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
            answers.removeLast()
//            Log.d(VideoblogsListFragment.TAG, "questionNumber: ${questionNumber}")
//            Log.d(VideoblogsListFragment.TAG, "questions.size: ${viewmodel.questions.size}")

            setQuestion()
        }

        binding.nextButton.setOnClickListener {


//             Remember the chosen button
            val checkId = binding.radioButtonGroup.checkedRadioButtonId

            var answerIndex = 0
            when (checkId){
                R.id.answer2button ->  answerIndex = 1
                R.id.answer3button -> answerIndex = 2
                R.id.answer4button -> answerIndex = 3
            }

            val question = OldQuestion(
                binding.questionText.text.toString(),
                listOf(
                    binding.answer1button.text.toString(),
                    binding.answer2button.text.toString(),
                    binding.answer3button.text.toString(),
//                    binding.answer4button.text.toString(),
                )
            )

            if (shuffledAnswers[answerIndex].isCorrect){
                answers.add(Answer(question, answerIndex, true ))
            } else {
                answers.add(Answer(question, answerIndex, false ))
            }



            if (questionNumber + 1 == quiz.questions.size){
//                Toast.makeText(requireContext(),
//                    "Congratulations!! You made $correctAnswers out of ${viewmodel.questions.size}",
//                    Toast.LENGTH_SHORT).show()
                val action = QuestionFragmentDirections.actionQuestionFragmentToReviewFragment(
                    answers.toTypedArray()
                )
                findNavController().navigate(action)

                return@setOnClickListener
            }
            questionNumber += 1
//
//            Log.d(VideoblogsListFragment.TAG, "questionNumber: ${questionNumber}")
//            Log.d(VideoblogsListFragment.TAG, "questions.size: ${viewmodel.questions.size}")

            setQuestion()



        }
    }

    private fun setQuestion() {

        if (questionNumber == 0){
            binding.prevButton.visibility = View.INVISIBLE
        } else {
            binding.prevButton.visibility = View.VISIBLE
        }

        if (questionNumber == quiz.questions.size - 1){
            binding.nextButton.text = "Завершить"
        } else {
            binding.nextButton.text = "Далее"
        }

        binding.questionNumber.text = "${questionNumber + 1}."

        val currentQuestion = quiz.questions[questionNumber]

        binding.questionText.text = currentQuestion.question

        shuffledAnswers = currentQuestion.options.toMutableList()
        shuffledAnswers.shuffle()

        binding.answer1button.text = shuffledAnswers[0].text
        binding.answer2button.text = shuffledAnswers[1].text
        binding.answer3button.text = shuffledAnswers[2].text
//        binding.answer4button.text = shuffledAnswers[3]

        Log.d(TAG, "setQuestion: ${answers}")
    }

    @Parcelize
    data class OldQuestion(
        val text: String,
        val options: List<String>
    ): Parcelable
}