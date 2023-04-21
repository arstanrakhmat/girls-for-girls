package com.example.girls4girls.presentation.videoblogsList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.girls4girls.R
import com.example.girls4girls.data.Category
import com.example.girls4girls.data.VideoBlog
import com.example.girls4girls.databinding.ItemCategoryBinding
import com.example.girls4girls.presentation.videoblog.VideoblogFragment.Companion.TAG

class CategoryAdapter: ListAdapter<Category, CategoryAdapter.CategoryViewHolder>
    (CategoryDiffUtil()) {

    var onCategoryClickListener: ((Category) -> Unit)? = null

    class CategoryViewHolder(
        item: View,
        private val onCategoryClickListener: ((Category) -> Unit)?
    ): RecyclerView.ViewHolder(item){
        private val binding = ItemCategoryBinding.bind(item)
        fun bind(category: Category) = with(binding){

            categoryName.text = category.name

            itemCategory.setOnClickListener {
                onCategoryClickListener?.invoke(category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view, onCategoryClickListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        holder.bind(getItem(position))
    }

    class CategoryDiffUtil: DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

    }
}