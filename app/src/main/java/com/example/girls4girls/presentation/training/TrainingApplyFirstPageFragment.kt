package com.example.girls4girls.presentation.training

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.girls4girls.R
import com.example.girls4girls.data.CustomPreferences
import com.example.girls4girls.data.model.QuestionnaireFillOut
import com.example.girls4girls.databinding.FragmentTrainingApplyFirstPageBinding
import com.example.girls4girls.presentation.questionarie.QuestionnaireRecyclerViewAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrainingApplyFirstPageFragment : Fragment() {

    private lateinit var binding: FragmentTrainingApplyFirstPageBinding
    private val trainingViewModel by viewModel<TrainingViewModel>()
    private val args by navArgs<TrainingApplyFirstPageFragmentArgs>()
    private lateinit var questionnaireRecyclerViewAdapter: QuestionnaireRecyclerViewAdapter
    private val sharedPreferences by inject<CustomPreferences>()
    private var questionnaireId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTrainingApplyFirstPageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
        initQuestionnaire()
        setupRv()

        trainingViewModel.getTrainingById(args.trainingId)
    }

    private fun initQuestionnaire() {
        trainingViewModel.trainingById.observe(requireActivity()) {
            questionnaireRecyclerViewAdapter.differ.submitList(it.questionnaire.questions)
            questionnaireId = it.questionnaire.id
            hideProgressBar()
        }

        trainingViewModel.error.observe(requireActivity()) {
//            Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_LONG)
//                .show()
            hideProgressBar()
            Log.d("questionnaire", "Error: $it.")
        }

        trainingViewModel.questionnaireApplySuccess.observe(requireActivity()) {
//            Toast.makeText(activity, "Questionnaire Saved", Toast.LENGTH_LONG).show()
            trainingViewModel.trainingApply(
                "Bearer ${sharedPreferences.fetchToken()}",
                args.trainingId,
                it.id
            )
            Log.d("TrainingInfo", "trainingId: ${args.trainingId}; questionnaireResponse: ${it.id}")
        }

        trainingViewModel.trainingApplySuccess.observe(requireActivity()) {
            findNavController().navigate(R.id.action_trainingApplyFirstPageFragment_to_trainingsListFragment)
            hideProgressBar()
            showCustomDialogBox()
        }
    }

    private fun setupRv() {
        questionnaireRecyclerViewAdapter = QuestionnaireRecyclerViewAdapter(trainingViewModel)
        binding.rvQuestionnaire.apply {
            adapter = questionnaireRecyclerViewAdapter
        }
    }

    private fun clickListeners() {
        binding.btnContinue.setOnClickListener {
            Log.d("questionnaire", trainingViewModel.testAnswer.toString())
            trainingViewModel.questionnaireApply2(
                "Bearer ${sharedPreferences.fetchToken()}",
                QuestionnaireFillOut(
                    questionnaireId,
                    trainingViewModel.testAnswer
                )
            )
            showProgressBar()
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

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}