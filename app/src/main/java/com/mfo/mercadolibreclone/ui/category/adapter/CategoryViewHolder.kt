package com.mfo.mercadolibreclone.ui.category.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mfo.mercadolibreclone.databinding.ItemCategoryBinding
import com.mfo.mercadolibreclone.domain.model.CategoryInfo

class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemCategoryBinding.bind(view)

    fun bind(categoryInfo: CategoryInfo, onItemSelected: (CategoryInfo) -> Unit) {
        val context = binding.tvTitle.context
        binding.ivCategory.setImageResource(categoryInfo.img)
        binding.tvTitle.text = context.getString(categoryInfo.name)

        binding.parent.setOnClickListener { onItemSelected(categoryInfo) }
    }
}