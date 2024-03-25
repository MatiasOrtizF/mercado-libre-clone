package com.mfo.mercadolibreclone.ui.globals.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mfo.mercadolibreclone.databinding.ItemProductBinding
import com.mfo.mercadolibreclone.domain.model.Product

class ProductViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemProductBinding.bind(view)

    fun bind(product: Product, onItemSelected: (Product) -> Unit) {
        //val context = binding.tvTitle.context
        //binding.ivCategory.setImageResource(categoryInfo.img)
        binding.tvTitle.text = product.title

        binding.parent.setOnClickListener { onItemSelected(product) }
    }
}