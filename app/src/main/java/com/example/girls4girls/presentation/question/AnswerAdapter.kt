package com.example.girls4girls.presentation.question

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.girls4girls.R
import com.example.girls4girls.data.Answer
import com.example.girls4girls.data.Question
import com.example.girls4girls.data.VideoBlog
import com.example.girls4girls.databinding.ItemAnswerBinding
import com.example.girls4girls.databinding.ItemVideoblogBinding
import com.example.girls4girls.presentation.mentorship.graduate.GraduateAdapter
import com.example.girls4girls.presentation.videoblog.VideoblogFragment.Companion.TAG

class AnswerAdapter: ListAdapter<Answer, AnswerAdapter.AnswerViewHolder>(AnswerDiffUtil()) {

    class AnswerViewHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ItemAnswerBinding.bind(item)
        fun bind(answer: Answer) = with(binding){

            val answerVariantsList = mutableListOf(
                answerVariant1,
                answerVariant2,
                answerVariant3,
//                answerVariant4
            )

            val answerVariantCardsList = mutableListOf(
                answerVariant1Card,
                answerVariant2Card,
                answerVariant3Card,
//                answerVariant4Card
            )

            val answerPunktsList = mutableListOf(
                answerPunkt1,
                answerPunkt2,
                answerPunkt3,
//                answerPunkt4
            )

            answerQuestion.text = answer.question.text


            for (variantId in 0..2){
                answerVariantsList[variantId].text = answer.question.options[variantId]

                if (variantId == answer.chosenId){

                    val isCorrectColor = if (answer.isCorrect){
                        Color.parseColor("#E6F4EA")
                    } else {
                        Color.parseColor("#FCE8E6")
                    }

                    answerVariantCardsList[variantId].setCardBackgroundColor(isCorrectColor)
                    answerPunktsList[variantId].setImageResource(R.drawable.ic_punkt_chosen)
                } else {
                    var defaultColor = Color.parseColor("#FFFFFF")
                    answerVariantCardsList[variantId].setCardBackgroundColor(defaultColor)
                    answerPunktsList[variantId].setImageResource(R.drawable.ic_punkt)
                }
            }

            if (answer.isCorrect){
                answerStatusImage.setImageResource(R.drawable.ic_right_answer)
            } else {
                answerStatusImage.setImageResource(R.drawable.ic_wrong_answer)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.item_answer, parent, false)
        return AnswerViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AnswerDiffUtil: DiffUtil.ItemCallback<Answer>(){
        override fun areItemsTheSame(oldItem: Answer, newItem: Answer): Boolean {
            return oldItem.question == newItem.question
        }

        override fun areContentsTheSame(oldItem: Answer, newItem: Answer): Boolean {
            return oldItem == newItem
        }

    }
}