package com.example.girls4girls.presentation.training

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.girls4girls.R
import com.example.girls4girls.databinding.FragmentTrainingApplySecondPageBinding

class TrainingApplySecondPageFragment : Fragment() {

    private lateinit var binding: FragmentTrainingApplySecondPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTrainingApplySecondPageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListeners()
    }

    private fun clickListeners() {
        binding.btnSendApplication.setOnClickListener {
            findNavController().navigate(R.id.action_trainingApplySecondPageFragment_to_trainingsListFragment)
            showCustomDialogBox()
        }
    }

    private fun showCustomDialogBox() {

        val messageBoxView = LayoutInflater.from(requireContext())
            .inflate(R.layout.alert_dialog_training_apply_success, null)

        val messageBox = AlertDialog.Builder(requireContext()).setView(messageBoxView)

        val messageBoxInstance = messageBox.show()

        messageBoxView.setOnClickListener {
            messageBoxInstance.dismiss()
        }
    }

}