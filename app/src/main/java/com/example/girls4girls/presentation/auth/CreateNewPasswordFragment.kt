package com.example.girls4girls.presentation.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.data.CustomPreferences
import com.example.girls4girls.databinding.FragmentCreateNewPasswordBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateNewPasswordFragment : Fragment() {

    private lateinit var binding: FragmentCreateNewPasswordBinding
    private val resetViewModel by viewModel<ResetPasswordViewModel>()
    private val sharedPreferences by inject<CustomPreferences>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCreateNewPasswordBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        binding.toolbar.back.setOnClickListener {
            findNavController().navigateUp()
        }

        clickListeners()
        setupObservers()
    }

    private fun clickListeners() {
        binding.btnSavePassword.setOnClickListener {
            if (!areFieldsEmpty()) {
                resetViewModel.changePassword(
                    "Bearer ${sharedPreferences.fetchToken()}",
                    binding.etPassword.text.toString()
                )
                showProgressBar()
            }
        }
    }

    private fun setupObservers() {
        resetViewModel.success.observe(requireActivity()) {
            Toast.makeText(
                requireContext(),
                "Пароль успешно обновлен",
                Toast.LENGTH_SHORT
            ).show()
            hideProgressBar()
            findNavController().navigate(R.id.action_createNewPasswordFragment_to_loginFragment)
        }

        resetViewModel.errorMessage.observe(requireActivity()) {
            Log.d("authE", it)
            Toast.makeText(requireContext(), "Неверный логин или пароль!", Toast.LENGTH_SHORT)
                .show()
            hideProgressBar()
        }
    }

    private fun areFieldsEmpty(): Boolean {
        val password = binding.etPassword.text.toString()
        val repeatPassword = binding.etPasswordRepeat.text.toString()

        val tvPasswordError = binding.tvPasswordError
        val tvPasswordRepeatError = binding.tvPasswordRepeatError

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