package com.example.girls4girls.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

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
    }

    private fun clickListeners() {
        binding.btnContinueRegistration.setOnClickListener {
            if (!areFieldsEmpty()) {
                findNavController().navigate(R.id.action_registrationFragment_to_registrationPartTwoFragment)
            }
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
            tvPhoneNumber.visibility = View.VISIBLE
            return true
        } else {
            tvPhoneNumber.visibility = View.GONE
        }

        if (email.isEmpty()) {
            tvEmailError.visibility = View.VISIBLE
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
}