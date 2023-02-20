package com.example.girls4girls.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentRegistrationSuccessBinding

class RegistrationSuccessFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationSuccessBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        binding.toolbar.userAccount.visibility = View.GONE
        binding.toolbar.back.visibility = View.GONE
        binding.toolbar.screenName.text = resources.getString(R.string.verification)
    }

}