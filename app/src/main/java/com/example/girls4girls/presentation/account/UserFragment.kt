package com.example.girls4girls.presentation.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.data.CustomPreferences
import com.example.girls4girls.databinding.FragmentUserBinding
import com.example.girls4girls.presentation.auth.LoginActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private val customPreferences by inject<CustomPreferences>()
    private val userViewModel by viewModel<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        userViewModel.getAllUserInfo("Bearer ${customPreferences.fetchToken()}")
        Log.d("authE", customPreferences.fetchToken().toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
        setupObservers()
    }

    private fun setupObservers() {
        userViewModel.userAllData.observe(requireActivity()) {
            with(binding) {
                userNameChange.text = it.firstName
                binding.btnLogOut.visibility = View.VISIBLE
                binding.signInFromUser.visibility = View.GONE
                binding.tvOr.visibility = View.GONE
                binding.registerFromUser.visibility = View.GONE
            }
        }

        userViewModel.errorMessage.observe(requireActivity()) {
            Log.d("profile", it)
            Toast.makeText(requireContext(), "Что-то пошло не так", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clickListeners() {
        binding.llModifyAccount.setOnClickListener {
            findNavController().navigate(R.id.action_userFragment_to_myInfoFragment)
        }

        binding.btnLogOut.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            activity?.finish()
        }

        binding.llLanguage.setOnClickListener {
            languageBottomSheet()
        }
    }

    private fun setLocale(language: String, context: Context) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        context.createConfigurationContext(configuration)
        context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
    }

    private fun setDefaultLocale(context: Context) {
        val locale = Locale.getDefault()
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        context.createConfigurationContext(configuration)
        context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
    }

    private fun languageBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(
            requireContext(),
            R.style.BottomSheetStyle
        )

        val view = layoutInflater.inflate(R.layout.bottom_sheet_language, null)

        view.findViewById<TextView>(R.id.russian).setOnClickListener {
            setDefaultLocale(requireContext())
            recreate(requireActivity())
            bottomSheetDialog.dismiss()
        }

        view.findViewById<TextView>(R.id.kyrgyz).setOnClickListener {
            setLocale("ky", requireContext())
            recreate(requireActivity())
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }
}