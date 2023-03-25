package com.example.girls4girls.presentation.question

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
                                                    args.answers.filter {ans ->
                                                        ans == true
                                                    }.size,
                                                    args.answers.size)
    }
}