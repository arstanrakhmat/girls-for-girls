package com.example.girls4girls.presentation.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentRegistrationPartTwoBinding
import com.example.girls4girls.utils.transformIntoDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationPartTwoFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationPartTwoBinding

    private val args by navArgs<RegistrationPartTwoFragmentArgs>()
    private val authViewModel by viewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationPartTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        clickListener()
        setupRegionMenu()
        setupToolbar()
        setupGenderMenu()
    }

    private fun clickListener() {
        binding.etBirthday.transformIntoDatePicker(requireContext(), "dd/MM/YYYY")

        binding.btnRegister.setOnClickListener {

            authViewModel.userSignUp(
                args.user.email,
                args.user.password,
                args.user.firstName,
                args.user.lastName,
                args.user.phoneNumber
            )
        }
    }

    private fun setupToolbar() {
        binding.toolbar.userAccount.visibility = View.GONE
        binding.toolbar.back.visibility = View.GONE
    }

    private fun setupObservers() {
        authViewModel.registered.observe(requireActivity()) {
            val action =
                RegistrationPartTwoFragmentDirections.actionRegistrationPartTwoFragmentToCodeFragment(
                    args.user.email,
                    args.user.phoneNumber
                )

            findNavController().navigate(action)
        }

        authViewModel.errorMessage.observe(requireActivity()) {
            Log.d("authError", it)
            Toast.makeText(requireContext(), "Error occurred", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRegionMenu() {
        val languages = resources.getStringArray(R.array.regions)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, languages)
        binding.etRegion.setAdapter(arrayAdapter)
    }

    private fun setupGenderMenu() {
        val genders = resources.getStringArray(R.array.genders)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, genders)
        binding.etGender.setAdapter(arrayAdapter)
    }


}