package com.example.girls4girls.presentation.account

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.data.CustomPreferences
import com.example.girls4girls.databinding.FragmentUserBinding
import com.example.girls4girls.presentation.auth.LoginActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private val customPreferences by inject<CustomPreferences>()
    private val userViewModel by viewModel<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        userViewModel.getUser("Bearer ${customPreferences.fetchToken()}")
        Log.d("authE", customPreferences.fetchToken().toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
        setupObservers()
    }

    private fun setupObservers() {
        userViewModel.user.observe(requireActivity()) {
            with(binding) {
//                userName.text = resources.getString(R.string.hello_registered_user)
                userName.text = "Привет, ${it.firstName}!"
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
    }
}