package com.mfo.mercadolibreclone.ui.globals.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfo.mercadolibreclone.databinding.ItemProductBinding
import com.mfo.mercadolibreclone.domain.model.Product

class ProductViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemProductBinding.bind(view)

    fun bind(product: Product, onItemSelected: (Product) -> Unit) {
        val context = binding.tvTitle.context
        //binding.ivCategory.setImageResource(categoryInfo.img)
        Glide.with(context).load(product.image).into(binding.ivProduct)
        binding.tvTitle.text = product.title
        binding.tvPrice.text = "$" + product.price.toString()
        binding.tvDescription.text = product.description

        binding.parent.setOnClickListener { onItemSelected(product) }
    }
}