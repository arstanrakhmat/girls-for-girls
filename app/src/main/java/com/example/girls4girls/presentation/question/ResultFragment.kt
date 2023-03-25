package com.example.girls4girls.presentation.question

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentResultBinding
import com.example.girls4girls.presentation.videoblog.VideoblogFragmentArgs

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private val args: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentResultBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scoreTxt.text = resources.getString(R.string.your_score,
            args.correctAnswers,
            args.questionsNumber)

        if (args.correctAnswers == args.questionsNumber){

//            binding.achievementIcon.visibility = View.VISIBLE

            binding.resultTxt.text = resources.getString(R.string.good_result)
            binding.resultDescription.text = resources.getString(R.string.good_result_description)
        } else {

            binding.achievementIcon.visibility = View.INVISIBLE

            binding.resultTxt.text = resources.getString(R.string.bad_result)
            binding.resultDescription.text = resources.getString(R.string.bad_result_description)
        }


    }
}