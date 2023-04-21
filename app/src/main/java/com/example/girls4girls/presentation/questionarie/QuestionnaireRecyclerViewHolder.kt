package com.example.girls4girls.presentation.questionarie

import android.text.Editable
import android.text.TextWatcher
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.girls4girls.data.model.Question
import com.example.girls4girls.databinding.ItemMultiplceChoiceBinding
import com.example.girls4girls.databinding.ItemOpenQuestionBinding
import com.example.girls4girls.databinding.ItemVariantsBinding
import com.example.girls4girls.presentation.training.TrainingViewModel

sealed class QuestionnaireRecyclerViewHolder(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    class OpenQuestionViewHolder(
        private val binding: ItemOpenQuestionBinding,
        private val viewModel: TrainingViewModel
    ) :
        QuestionnaireRecyclerViewHolder(binding) {

        fun bind(question: Question) {
            binding.tvQuestion.text = question.text
            binding.etAnswer.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    viewModel.addOnTextSelected2(question.id, p0.toString())
                }
            })
        }
    }

    class VariantsQuestionViewHolder(
        private val binding: ItemVariantsBinding,
        private val viewModel: TrainingViewModel
    ) :
        QuestionnaireRecyclerViewHolder(binding) {

        fun bind(question: Question) {
            binding.tvQuestion.text = question.text

            binding.radioGroup.removeAllViews()

            question.variants.forEachIndexed { index, variant ->
                val radioButton = RadioButton(itemView.context)
                radioButton.text = variant.text
                binding.radioGroup.addView(radioButton)

                radioButton.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        viewModel.addOnVariantSelected2(question.id, index)
                    } else {
                        viewModel.deleteOnVariantSelected2(question.id, index)
                    }
                }
            }
        }
    }

    class CheckBoxQuestionViewHolder(
        private val binding: ItemMultiplceChoiceBinding,
        private val viewModel: TrainingViewModel
    ) :
        QuestionnaireRecyclerViewHolder(binding) {

        fun bind(question: Question) {
            binding.tvQuestion.text = question.text

            question.variants.forEachIndexed { index, variant ->
                val checkBox = CheckBox(itemView.context)
                checkBox.text = variant.text
                binding.llCheckBox.addView(checkBox)

                checkBox.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        viewModel.addChoice2(question.id, index)
                    }
                }
            }
        }
    }
}