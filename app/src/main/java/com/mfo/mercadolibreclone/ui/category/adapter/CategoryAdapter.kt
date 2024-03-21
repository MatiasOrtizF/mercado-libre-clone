package com.mfo.mercadolibreclone.ui.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfo.mercadolibreclone.R
import com.mfo.mercadolibreclone.domain.model.CategoryInfo

class CategoryAdapter(private var categoryList: List<CategoryInfo> = emptyList(), private val onItemSelected:(CategoryInfo) -> Unit ): RecyclerView.Adapter<CategoryViewHolder>() {

    fun updateList(list: List<CategoryInfo>) {
        categoryList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position], onItemSelected)
    }
}