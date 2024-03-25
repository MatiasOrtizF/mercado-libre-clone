package com.mfo.mercadolibreclone.ui.globals.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfo.mercadolibreclone.R
import com.mfo.mercadolibreclone.domain.model.Product

class ProductAdapter(private var productsList: List<Product> = emptyList(), private val onItemSelected:(Product) -> Unit): RecyclerView.Adapter<ProductViewHolder>() {
    fun updateList(list: List<Product>) {
        productsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productsList[position], onItemSelected)
    }
}