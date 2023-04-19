package com.example.girls4girls.presentation.questionarie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.girls4girls.data.model.Question
import com.example.girls4girls.databinding.ItemMultiplceChoiceBinding
import com.example.girls4girls.databinding.ItemOpenQuestionBinding
import com.example.girls4girls.databinding.ItemVariantsBinding
import com.example.girls4girls.presentation.training.TrainingViewModel

class QuestionnaireRecyclerViewAdapter(private val viewModel: TrainingViewModel) :
    RecyclerView.Adapter<QuestionnaireRecyclerViewHolder>() {

    private val TEXT_VIEW_TYPE = 1
    private val VARIANTS_VIEW_TYPE = 2
    private val CHECK_BOX = 3

    private val differCallback = object : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuestionnaireRecyclerViewHolder {
        return when (viewType) {
            TEXT_VIEW_TYPE -> {
                QuestionnaireRecyclerViewHolder.OpenQuestionViewHolder(
                    ItemOpenQuestionBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    viewModel
                )
            }

            VARIANTS_VIEW_TYPE -> {
                QuestionnaireRecyclerViewHolder.VariantsQuestionViewHolder(
                    ItemVariantsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    viewModel
                )
            }

            CHECK_BOX -> {
                QuestionnaireRecyclerViewHolder.CheckBoxQuestionViewHolder(
                    ItemMultiplceChoiceBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    viewModel
                )
            }

            else -> throw IllegalArgumentException("Invalid view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: QuestionnaireRecyclerViewHolder, position: Int) {
        when (holder) {
            is QuestionnaireRecyclerViewHolder.OpenQuestionViewHolder -> holder.bind(differ.currentList[position])
            is QuestionnaireRecyclerViewHolder.VariantsQuestionViewHolder -> holder.bind(differ.currentList[position])
            is QuestionnaireRecyclerViewHolder.CheckBoxQuestionViewHolder -> holder.bind(differ.currentList[position])
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (differ.currentList[position].type) {
            "TEXT" -> TEXT_VIEW_TYPE
            "VARIANTS" -> VARIANTS_VIEW_TYPE
            "CHECK_BOX" -> CHECK_BOX
            else -> throw IllegalArgumentException(
                "Invalid question type: ${differ.currentList[position].type}"
            )
        }
    }
}