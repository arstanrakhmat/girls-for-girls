package com.example.girls4girls.presentation.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentMyInfoBinding
import com.example.girls4girls.utils.transformIntoDatePicker
import com.google.android.material.bottomsheet.BottomSheetDialog

class MyInfoFragment : Fragment() {

    private lateinit var binding: FragmentMyInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyInfoBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListeners()
    }

    private fun clickListeners() {
        binding.etGender.setOnClickListener {
            genderBottomSheet()
        }

        binding.etRegion.setOnClickListener {
            regionBottomSheet()
        }

        binding.etBirtday.setOnClickListener {
            binding.etBirtday.transformIntoDatePicker(requireContext(), "dd.MM.yyyy")
        }

        binding.llChangerPassword.setOnClickListener {
            ChangePasswordSheetFragment().showNow(this.parentFragmentManager, "newSheet")
        }
    }

    private fun genderBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(
            requireContext(),
            R.style.BottomSheetStyle
        )
        val view = layoutInflater.inflate(R.layout.bottom_sheet_gender, null)

        view.findViewById<ImageView>(R.id.btnClose).setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        view.findViewById<TextView>(R.id.boy).setOnClickListener {
            val gender = view.findViewById<TextView>(R.id.boy).text.toString()
            binding.etGender.setText(gender)
            bottomSheetDialog.dismiss()
        }

        view.findViewById<TextView>(R.id.girl).setOnClickListener {
            val gender = view.findViewById<TextView>(R.id.girl).text.toString()
            binding.etGender.setText(gender)
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    private fun regionBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(
            requireContext(),
            R.style.BottomSheetStyle
        )
        val view = layoutInflater.inflate(R.layout.bottom_sheet_region, null)

        view.findViewById<ImageView>(R.id.btnClose).setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        view.findViewById<TextView>(R.id.bishek).setOnClickListener {
            val region = view.findViewById<TextView>(R.id.bishek).text.toString()
            binding.etRegion.setText(region)
            bottomSheetDialog.dismiss()
        }

        view.findViewById<TextView>(R.id.chuy).setOnClickListener {
            val region = view.findViewById<TextView>(R.id.chuy).text.toString()
            binding.etRegion.setText(region)
            bottomSheetDialog.dismiss()
        }

        view.findViewById<TextView>(R.id.issyKol).setOnClickListener {
            val region = view.findViewById<TextView>(R.id.issyKol).text.toString()
            binding.etRegion.setText(region)
            bottomSheetDialog.dismiss()
        }

        view.findViewById<TextView>(R.id.talas).setOnClickListener {
            val region = view.findViewById<TextView>(R.id.talas).text.toString()
            binding.etRegion.setText(region)
            bottomSheetDialog.dismiss()
        }

        view.findViewById<TextView>(R.id.naryn).setOnClickListener {
            val region = view.findViewById<TextView>(R.id.naryn).text.toString()
            binding.etRegion.setText(region)
            bottomSheetDialog.dismiss()
        }

        view.findViewById<TextView>(R.id.dzalalAbad).setOnClickListener {
            val region = view.findViewById<TextView>(R.id.dzalalAbad).text.toString()
            binding.etRegion.setText(region)
            bottomSheetDialog.dismiss()
        }

        view.findViewById<TextView>(R.id.osh).setOnClickListener {
            val region = view.findViewById<TextView>(R.id.osh).text.toString()
            binding.etRegion.setText(region)
            bottomSheetDialog.dismiss()
        }

        view.findViewById<TextView>(R.id.batken).setOnClickListener {
            val region = view.findViewById<TextView>(R.id.batken).text.toString()
            binding.etRegion.setText(region)
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

}