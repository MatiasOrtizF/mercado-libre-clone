package com.mfo.mercadolibreclone.ui.search.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mfo.mercadolibreclone.databinding.ItemHistoryBinding

class SearchViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemHistoryBinding.bind(view)

    fun bind(historyItem: String, onItemSelected: (String) -> Unit) {
        val context = binding.tvItemSearch.context
        binding.tvItemSearch.text = historyItem
    }
}