package com.example.girls4girls.presentation.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentCodeBinding

class CodeFragment : Fragment() {

    private lateinit var binding: FragmentCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCodeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addTextChangeListener()
    }

    private fun addTextChangeListener() {
        binding.et1.addTextChangedListener(EditTextWatcher(binding.et1))
        binding.et2.addTextChangedListener(EditTextWatcher(binding.et2))
        binding.et3.addTextChangedListener(EditTextWatcher(binding.et3))
        binding.et4.addTextChangedListener(EditTextWatcher(binding.et4))
        binding.et5.addTextChangedListener(EditTextWatcher(binding.et5))
        binding.et6.addTextChangedListener(EditTextWatcher(binding.et6))
    }

    inner class EditTextWatcher(private val view: View) : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {

            val text = p0.toString()
            when (view.id) {
                R.id.et_1 -> if (text.length == 1) binding.et2.requestFocus()
                R.id.et_2 -> if (text.length == 1) binding.et3.requestFocus() else if (text.isEmpty()) binding.et1.requestFocus()
                R.id.et_3 -> if (text.length == 1) binding.et4.requestFocus() else if (text.isEmpty()) binding.et2.requestFocus()
                R.id.et_4 -> if (text.length == 1) binding.et5.requestFocus() else if (text.isEmpty()) binding.et3.requestFocus()
                R.id.et_5 -> if (text.length == 1) binding.et6.requestFocus() else if (text.isEmpty()) binding.et4.requestFocus()
                R.id.et_6 -> if (text.isEmpty()) binding.et5.requestFocus()
            }
        }
    }

}