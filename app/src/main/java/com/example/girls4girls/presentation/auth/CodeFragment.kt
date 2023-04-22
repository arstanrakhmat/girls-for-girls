package com.example.girls4girls.presentation.auth

import android.os.Bundle
import android.os.CountDownTimer
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
import com.example.girls4girls.databinding.FragmentCodeBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CodeFragment : Fragment() {

    private lateinit var binding: FragmentCodeBinding

    private val args by navArgs<CodeFragmentArgs>()
    private val authViewModel by viewModel<AuthViewModel>()
    private val sharedPreferences by inject<CustomPreferences>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCodeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        clickListener()
        setupToolbar()
        setupPhoneNumber()
    }

    private fun setupObservers() {
        authViewModel.activated.observe(requireActivity()) {
            findNavController().navigate(R.id.action_codeFragment_to_registrationSuccessFragment)
            sharedPreferences.saveToken(it.access_token)
        }

        authViewModel.resendOtp.observe(requireActivity()) {
            Toast.makeText(
                requireContext(),
                "Вам был отправлен новый код потверждения",
                Toast.LENGTH_SHORT
            ).show()
        }

        authViewModel.errorMessage.observe(requireActivity()) {
            binding.tvOtpError.visibility = View.VISIBLE
            Log.d("auth", it)
            Toast.makeText(requireContext(), "Incorrect otp", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clickListener() {
        binding.btnVerify.setOnClickListener {
            val otp = binding.pinView.text?.trim().toString()

            if (otp.length < 6) {
                binding.tvOtpError.visibility = View.VISIBLE
            } else {
                authViewModel.userActivate(args.userResend.email, args.userResend.phoneNumber, otp)
            }
        }

        binding.btnSendCodeAgain.setOnClickListener {
            startTimer()
            authViewModel.resendOtp(
                args.userResend.email,
                args.userResend.password,
                args.userResend.firstName,
                args.userResend.lastName,
                args.userResend.phoneNumber
            )
        }

        binding.toolbar.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun startTimer() {
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.btnSendCodeAgain.text = "Осталось: 00:${millisUntilFinished / 1000}"
                binding.btnSendCodeAgain.isEnabled = false
            }

            override fun onFinish() {
                binding.btnSendCodeAgain.text = resources.getString(R.string.send_code_again)
                binding.btnSendCodeAgain.isEnabled = true
            }
        }.start()
    }

    private fun setupToolbar() {
        binding.toolbar.userAccount.visibility = View.GONE
//        binding.toolbar.back.visibility = View.GONE
        binding.toolbar.screenName.text = resources.getString(R.string.verification)
    }

    private fun setupPhoneNumber() {
        binding.tv4.text = args.userResend.phoneNumber
        binding.tv2.text = args.userResend.email
    }
}