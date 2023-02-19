package com.example.girls4girls.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentRegistrationPartTwoBinding
import com.example.girls4girls.presentation.other.transformIntoDatePicker

class RegistrationPartTwoFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationPartTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationPartTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListener()
        setupRegionMenu()
        setupGenderMenu()
    }

    private fun clickListener() {
        binding.etBirthday.transformIntoDatePicker(requireContext(), "dd/MM/YYYY")
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