package com.example.girls4girls.presentation.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.girls4girls.R
import com.example.girls4girls.data.CustomPreferences
import com.example.girls4girls.databinding.FragmentMainUserPageBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainUserPageFragment : Fragment() {

    private lateinit var binding: FragmentMainUserPageBinding
    private lateinit var jetonAdapter: JetonAdapter
    private lateinit var videoJetonAdapter: JetonVideoAdapter
    private val customPreferences by inject<CustomPreferences>()
    private val userViewModel by viewModel<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainUserPageBinding.inflate(inflater, container, false)
        userViewModel.getAllUserInfo("Bearer ${customPreferences.fetchToken()}")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        clickListener()
        setupObservers()
    }

    private fun setupObservers() {
        userViewModel.userAllData.observe(requireActivity()) {
            binding.userName.text = it.firstName
            if (it.image != null) {
                Glide.with(requireActivity()).load(it.image.url).into(binding.userImage)
            }

            if (!it.jetons.isNullOrEmpty()) {
//                trainingAdapter.differ.submitList(it.data)
//                jetonAdapter.differ.submitList(it.jetons)
//                videoJetonAdapter.differ.submitList(it.jetons)
                for (jeton in it.jetons) {
                    if (jeton.type == "CARD") {
                        jetonAdapter.differ.submitList(it.jetons)
                    } else {
                        videoJetonAdapter.differ.submitList(it.jetons)
                    }
                }
            }
        }
    }

    private fun clickListener() {
        binding.btnGoToSettings.setOnClickListener {
            findNavController().navigate(R.id.action_mainUserPageFragment_to_userFragment)
        }

        jetonAdapter.setOnJetonClickListener {
            val bundle = Bundle().apply {
                putSerializable("cardInfo", it)
            }

            val dialogFragment = JetonDialogFragment()
            dialogFragment.arguments = bundle
            dialogFragment.show(parentFragmentManager, "myDialog")
        }
    }

    private fun setupRecyclerView() {
        jetonAdapter = JetonAdapter()
        binding.rvMyCards.apply {
            adapter = jetonAdapter
        }

        videoJetonAdapter = JetonVideoAdapter()
        binding.rvMyVideoCards.apply {
            adapter = videoJetonAdapter
        }
    }

}