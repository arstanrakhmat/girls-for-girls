package com.example.girls4girls.presentation.mentorship

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.girls4girls.R

class MentorshipFragment : Fragment() {

    companion object {
        fun newInstance() = MentorshipFragment()
    }

    private lateinit var viewModel: MentorshipViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mentorship, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MentorshipViewModel::class.java)
        // TODO: Use the ViewModel
    }

}