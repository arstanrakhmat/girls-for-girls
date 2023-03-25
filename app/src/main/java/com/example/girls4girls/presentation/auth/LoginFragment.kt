package com.example.girls4girls.presentation.auth

import android.content.Intent
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
import com.example.girls4girls.data.CustomPreferences
import com.example.girls4girls.databinding.FragmentLoginBinding
import com.example.girls4girls.presentation.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val authViewModel by viewModel<AuthViewModel>()
    private lateinit var sharedPreferences: CustomPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = CustomPreferences(requireContext())
        setUpObservers()
        clickListeners()
    }

    private fun clickListeners() {
        binding.signInWelcomePage.setOnClickListener {

            if (!areFieldsEmpty()) {
                loginValidation(binding.etLoginOrNumber.text.toString())
            }
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        binding.btnForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
        }
    }

    private fun setUpObservers() {
        authViewModel.token.observe(requireActivity()) {

            startActivity(Intent(requireContext(), MainActivity::class.java))
            sharedPreferences.saveToken(it.access_token)
            Log.d("authE", it.access_token)
            hideProgressBar()
        }

        authViewModel.errorMessage.observe(requireActivity()) {
            Log.d("authE", it)
            Toast.makeText(requireContext(), "Неверный логин или пароль!", Toast.LENGTH_SHORT)
                .show()
            hideProgressBar()
        }
    }

    private fun loginValidation(login: String) {
        if (isLoginEmail(login)) {
            if (isGmail(login)) {
//                Toast.makeText(
//                    requireContext(),
//                    "Valid login",
//                    Toast.LENGTH_SHORT
//                ).show()

                authViewModel.userLoginEmail(login, binding.etPassword.text.toString())
                showProgressBar()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Неправильно ввели данные",
                    Toast.LENGTH_SHORT
                ).show()
            }

        } else if (isPhoneNumber(login)) {
            if (isKgNumber(login)) {
//                Toast.makeText(
//                    requireContext(),
//                    "Valid number",
//                    Toast.LENGTH_SHORT
//                ).show()
                authViewModel.userLoginPhoneNumber(login, binding.etPassword.text.toString())
                showProgressBar()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Номер должен начинаться с 996",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                requireContext(),
                "Неправильно ввели данные",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun areFieldsEmpty(): Boolean {

        val login = binding.etLoginOrNumber.text.toString()
        val password = binding.etPassword.text.toString()

        val tvLogin = binding.tvEmailDoesNotExist
        val tvPassword = binding.tvPasswordIsEmpty

        if (login.isEmpty()) {
            tvLogin.text = resources.getString(R.string.login_is_empty)
            tvLogin.visibility = View.VISIBLE
            return true
        } else {
            tvLogin.visibility = View.GONE
        }

        if (password.isEmpty()) {
            tvPassword.visibility = View.VISIBLE
            return true
        } else {
            tvLogin.visibility = View.GONE
        }

        if (password.length < 8) {
            tvPassword.text = resources.getString(R.string.password_length_incorrect)
            tvPassword.visibility = View.VISIBLE
            return true
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

    private fun showProgressBar() {
        binding.signInWelcomePage.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.signInWelcomePage.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

}
