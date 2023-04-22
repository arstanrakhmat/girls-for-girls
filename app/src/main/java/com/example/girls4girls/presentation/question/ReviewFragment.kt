package com.example.girls4girls.presentation.question

import android.app.Dialog
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
import com.example.girls4girls.databinding.JetonQuiz1Binding

class ReviewFragment : Fragment() {

    private lateinit var mainBinding: FragmentReviewBinding
    private lateinit var answerAdapter: AnswerAdapter
    private val args by navArgs<ReviewFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainBinding = FragmentReviewBinding.inflate(inflater, container, false)
        return mainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showDialog()

        answerAdapter = AnswerAdapter()

        mainBinding.answersList.adapter = answerAdapter
        answerAdapter.submitList(args.answers.toList())

    }

    fun showDialog(){
        val dialog = Dialog(requireContext())
        val binding = JetonQuiz1Binding.inflate(LayoutInflater.from(mainBinding.root.context))
        dialog.setContentView(binding.root)

        binding.thanksButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }
}