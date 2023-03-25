package com.example.girls4girls.presentation.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.girls4girls.R
import com.example.girls4girls.data.CustomPreferences
import com.example.girls4girls.databinding.FragmentResetPasswordCodeBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResetPasswordCodeFragment : Fragment() {

    private lateinit var binding: FragmentResetPasswordCodeBinding
    private val args by navArgs<ResetPasswordCodeFragmentArgs>()
    private val resetViewModel by viewModel<ResetPasswordViewModel>()
    private val sharedPreferences by inject<CustomPreferences>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentResetPasswordCodeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        binding.toolbar.back.setOnClickListener {
            findNavController().navigateUp()
        }
        clickListener()
        setupObservers()
    }

    private fun clickListener() {
        binding.btnVerify.setOnClickListener {
            val otp = binding.pinView.text?.trim().toString()

            if (otp.length < 6) {
                binding.tvOtpError.visibility = View.VISIBLE
            } else {
                resetViewModel.resetPasswordEmailConfirm(args.emailPhoneNum, otp)
                showProgressBar()
            }
        }
    }

    private fun setupObservers() {
        resetViewModel.token.observe(requireActivity()) {
            sharedPreferences.saveToken(it.access_token)
            findNavController().navigate(R.id.action_resetPasswordCodeFragment_to_createNewPasswordFragment)
            hideProgressBar()
        }

        resetViewModel.errorMessage.observe(requireActivity()) {
            Log.d("authE", it)
            Toast.makeText(requireContext(), "Неверный логин или пароль!", Toast.LENGTH_SHORT)
                .show()
            hideProgressBar()
        }
    }

    private fun setupToolbar() {
        binding.toolbar.userAccount.visibility = View.GONE
        binding.toolbar.screenName.text = resources.getString(R.string.reset_password_toolbar)
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}