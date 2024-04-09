package com.mfo.mercadolibreclone.ui.searchDetail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfo.mercadolibreclone.databinding.ItemProductBinding
import com.mfo.mercadolibreclone.domain.model.Product

class SearchDetailViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemProductBinding.bind(view)

    fun bind(product: Product, onItemSelected: (Product) -> Unit, onFavoriteButtonClicked: (Long) -> Unit) {
        val context = binding.tvTitle.context
        //binding.ivCategory.setImageResource(categoryInfo.img)
        Glide.with(context).load(product.image).into(binding.ivProduct)
        binding.tvTitle.text = product.title
        binding.tvPrice.text = "$${product.price}"
        binding.tvDescription.text = product.description

        binding.btnAddFavorite.setOnClickListener { onFavoriteButtonClicked(product.id) }

        binding.parent.setOnClickListener { onItemSelected(product) }
    }
}