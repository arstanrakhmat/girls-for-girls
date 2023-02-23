package com.example.girls4girls.presentation.auth

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.data.model.UserRegistration
import com.example.girls4girls.databinding.FragmentRegistrationBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private val authViewModel by viewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
        setupToolbar()
        setupObservers()
    }

    private fun clickListeners() {
        binding.btnContinueRegistration.setOnClickListener {
            if (!areFieldsEmpty()) {
                authViewModel.userSignUp(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString(),
                    binding.etName.text.toString(),
                    binding.etLastName.text.toString(),
                    binding.etPhoneNumber.text.toString()
                )
            } else {
                Toast.makeText(requireContext(), "!!!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbar.userAccount.visibility = View.GONE
        binding.toolbar.back.visibility = View.GONE
    }

    private fun setupObservers() {
        authViewModel.registered.observe(requireActivity()) {
            val action =
                RegistrationFragmentDirections.actionRegistrationFragmentToCodeFragment(
                    UserRegistration(
                        binding.etEmail.text.toString(),
                        binding.etPassword.text.toString(),
                        binding.etName.text.toString(),
                        binding.etLastName.text.toString(),
                        binding.etPhoneNumber.text.toString()
                    )
                )

            Toast.makeText(requireContext(), "In Success", Toast.LENGTH_LONG).show()

            findNavController().navigate(action)
        }

        authViewModel.errorMessage.observe(requireActivity()) {
            Log.d("authError", it)
            Toast.makeText(
                requireContext(),
                "Пользователь с такими данными уже существует",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun areFieldsEmpty(): Boolean {

        val name = binding.etName.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val repeatPassword = binding.etPasswordRepeat.text.toString()

        val tvNameError = binding.tvNameError
        val tvPhoneNumber = binding.tvNumberError
        val tvEmailError = binding.tvEmailError
        val tvPasswordError = binding.tvPasswordError
        val tvPasswordRepeatError = binding.tvPasswordRepeatError

        if (name.isEmpty()) {
            tvNameError.visibility = View.VISIBLE
            return true
        } else {
            tvNameError.visibility = View.GONE
        }

        if (phoneNumber.isEmpty()) {
            tvPhoneNumber.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.error_color
                )
            )
            tvPhoneNumber.text = resources.getString(R.string.fill_out_the_blank)
            return true
        } else if (!isKgNumber(phoneNumber)) {
            tvPhoneNumber.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.error_color
                )
            )
            tvPhoneNumber.text = resources.getString(R.string.incorrect_type_number)
            return true
        } else {
            tvPhoneNumber.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.gray
                )
            )
            tvPhoneNumber.text = resources.getString(R.string._0771677010_example)
        }

        if (email.isEmpty()) {
            tvEmailError.visibility = View.VISIBLE
            return true
        } else {
            tvEmailError.visibility = View.GONE
        }

        if (!isLoginEmail(email) || !isGmail(email)) {
            tvEmailError.visibility = View.VISIBLE
            tvEmailError.text = resources.getString(R.string.incorrect_type_email)
            return true
        } else {
            tvEmailError.visibility = View.GONE
        }

        if (password.length < 8) {
            tvPasswordError.visibility = View.VISIBLE
            return true
        } else {
            tvPasswordError.visibility = View.GONE
        }

        if (repeatPassword.compareTo(password) != 0) {
            tvPasswordRepeatError.visibility = View.VISIBLE
            return true
        } else {
            tvPasswordRepeatError.visibility = View.GONE
        }

        return false
    }

    private fun isGmail(string: String): Boolean {
        val pattern = Regex("\\b[A-Za-z0-9._%+-]+@gmail\\.com\\b")
        return pattern.matches(string)
    }

    private fun isKgNumber(string: String): Boolean {
        val pattern = Regex("^996\\d{9}$")
        return pattern.matches(string)
    }

    private fun isLoginEmail(login: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(login).matches()
    }
}