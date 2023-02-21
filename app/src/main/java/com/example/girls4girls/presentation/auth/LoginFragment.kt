package com.example.girls4girls.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListeners()
//        passwordLengthListener(binding.etPassword, binding.signInWelcomePage)
    }

    private fun clickListeners() {
        binding.signInWelcomePage.setOnClickListener {

            if (!areFieldsEmpty()) {
                if (!isPhoneNumber(binding.etLoginOrNumber.text.toString())) {
                    Toast.makeText(requireContext(), "This is email", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "This is phoneNumber", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
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

    private fun isPhoneNumber(characters: String): Boolean {
        for (character in characters) {
            if (character in 'a'..'z' || character in 'A'..'Z') {
                return false
            }
        }

        return true
    }

//    private fun passwordLengthListener(et: TextInputEditText, button: Button) {
//        et.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                if (p0.toString().trim().length < 8) {
//                    button.backgroundTintList =
//                        ColorStateList.valueOf(Color.RED)
//
//                    Toast.makeText(requireContext(), "length", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//        })
//    }
}
