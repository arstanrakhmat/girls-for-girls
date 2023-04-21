package com.example.girls4girls.presentation.auth

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentResetPasswordBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResetPasswordFragment : Fragment() {

    private lateinit var binding: FragmentResetPasswordBinding
    private val resetViewModel by viewModel<ResetPasswordViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupObservers()
        clickListeners()
        binding.toolbar.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun clickListeners() {
        binding.btnReset.setOnClickListener {
            if (!areFieldsEmpty()) {
                loginValidation(binding.etLoginOrNumber.text.toString())
            }
        }
    }

    private fun setupObservers() {
        resetViewModel.success.observe(requireActivity()) {
            Toast.makeText(
                requireContext(),
                "Code was sent",
                Toast.LENGTH_SHORT
            ).show()

            hideProgressBar()

            val action =
                ResetPasswordFragmentDirections.actionResetPasswordFragmentToResetPasswordCodeFragment(
                    binding.etLoginOrNumber.text.toString()
                )

            findNavController().navigate(action)
        }

        resetViewModel.errorMessage.observe(requireActivity()) {
            Log.d("authE", it)
            Toast.makeText(requireContext(), "Неверный логин или пароль!", Toast.LENGTH_SHORT)
                .show()
            hideProgressBar()
        }
    }

    private fun loginValidation(login: String) {
        if (isLoginEmail(login)) {
            if (isGmail(login)) {
                Toast.makeText(
                    requireContext(),
                    "Valid login",
                    Toast.LENGTH_SHORT
                ).show()

                resetViewModel.resetPasswordEmail(binding.etLoginOrNumber.text.toString())
                showProgressBar()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Not appropriate email",
                    Toast.LENGTH_SHORT
                ).show()
            }

        } else if (isPhoneNumber(login)) {
            if (isKgNumber(login)) {
                Toast.makeText(
                    requireContext(),
                    "Resseting password with phone number will be done later",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "996 must have",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                requireContext(),
                "Not appropriate data",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun areFieldsEmpty(): Boolean {
        val login = binding.etLoginOrNumber.text.toString()
        val tvLogin = binding.tvEmailDoesNotExist

        if (login.isEmpty()) {
            tvLogin.text = resources.getString(R.string.login_is_empty)
            tvLogin.visibility = View.VISIBLE
            return true
        } else {
            tvLogin.visibility = View.GONE
        }

        return false
    }

    private fun isLoginEmail(login: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(login).matches()
    }

    private fun isGmail(string: String): Boolean {
        val pattern = Regex("\\b[A-Za-z0-9._%+-]+@gmail\\.com\\b")
        return pattern.matches(string)
    }

    private fun isPhoneNumber(string: String): Boolean {
        val pattern = Regex("^[\\d\\-\\s().]+$")
        return pattern.matches(string)
    }

    private fun isKgNumber(string: String): Boolean {
        val pattern = Regex("^996\\d{9}$")
        return pattern.matches(string)
    }

    private fun setupToolbar() {
//        binding.toolbar.back.visibility = View.GONE
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