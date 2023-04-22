package com.example.girls4girls.presentation.question

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.girls4girls.R
import com.example.girls4girls.data.Answer
import com.example.girls4girls.data.CustomPreferences
import com.example.girls4girls.databinding.FragmentQuestionBinding
import com.example.girls4girls.databinding.FragmentReviewBinding
import com.example.girls4girls.databinding.JetonQuiz1Binding
import com.example.girls4girls.presentation.videoblog.VideoblogFragment.Companion.TAG
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReviewFragment : Fragment() {

    private lateinit var mainBinding: FragmentReviewBinding
    private lateinit var answerAdapter: AnswerAdapter
    private val args by navArgs<ReviewFragmentArgs>()
    private val viewModel by viewModel<ReviewViewModel>()
    private val sharedPreferences by inject<CustomPreferences>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainBinding = FragmentReviewBinding.inflate(inflater, container, false)
        return mainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getJeton("Bearer ${sharedPreferences.fetchToken()}")

        showDialog()

        answerAdapter = AnswerAdapter()

        mainBinding.answersList.adapter = answerAdapter
        answerAdapter.submitList(args.answers.toList())

    }

    fun showDialog(){
        viewModel._jeton.observe(viewLifecycleOwner){jeton ->
            val dialog = Dialog(requireContext())
            val binding = JetonQuiz1Binding.inflate(LayoutInflater.from(mainBinding.root.context))
            dialog.setContentView(binding.root)

            Glide
                .with(this)
                .load(jeton.image.url)
                .into(binding.jetonImage)

            Log.d(TAG, "showDialog: ${jeton.image.url}")

            binding.jetonTitle.text = jeton.title
            binding.jetonDescription.text = jeton.description

            binding.thanksButton.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }


    }
}