package com.mfo.mercadolibreclone.ui.searchDetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfo.mercadolibreclone.R
import com.mfo.mercadolibreclone.domain.model.Product

class SearchDetailAdapter (private var productsList: MutableList<Product> = mutableListOf(), private val onItemSelected:(Product) -> Unit): RecyclerView.Adapter<SearchDetailViewHolder>() {
    fun updateList(list: MutableList<Product>) {
        productsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchDetailViewHolder {
        return SearchDetailViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: SearchDetailViewHolder, position: Int) {
        holder.bind(productsList[position], onItemSelected)
    }
}