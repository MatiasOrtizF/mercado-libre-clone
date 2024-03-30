package com.mfo.mercadolibreclone.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfo.mercadolibreclone.R

class SearchAdapter(private var searchList: List<String> = emptyList(), private val onItemSelected:(String) -> Unit): RecyclerView.Adapter<SearchViewHolder>() {
    fun updateList(list: List<String>) {
        searchList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchList[position], onItemSelected)
    }

}