package com.example.girls4girls.presentation.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.girls4girls.databinding.FragmentChangePasswordSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChangePasswordSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentChangePasswordSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentChangePasswordSheetBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }


}