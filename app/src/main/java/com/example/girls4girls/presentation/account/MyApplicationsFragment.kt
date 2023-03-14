package com.example.girls4girls.presentation.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.girls4girls.databinding.FragmentMyApplicationsBinding

class MyApplicationsFragment : Fragment() {

    private lateinit var binding: FragmentMyApplicationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyApplicationsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }
}