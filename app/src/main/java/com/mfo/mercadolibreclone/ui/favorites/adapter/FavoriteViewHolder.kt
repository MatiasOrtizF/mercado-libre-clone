package com.mfo.mercadolibreclone.ui.favorites.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfo.mercadolibreclone.data.network.response.FavoriteResponse
import com.mfo.mercadolibreclone.databinding.ItemFavoriteBinding

class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemFavoriteBinding.bind(view)

    fun bind(favorite: FavoriteResponse, onItemSelected: (FavoriteResponse) -> Unit, onFavoriteDeleteButtonClicked: (Long) -> Unit) {
        val context=  binding.tvTitle.context
        Glide.with(context).load(favorite.product.image).into(binding.ivProduct)
        binding.tvTitle.text = favorite.product.title
        binding.tvPrice.text = "$${favorite.product.price}"
        //binding.tvAgeKm.text = favorite.product.description

        binding.btnDeleteFav.setOnClickListener { onFavoriteDeleteButtonClicked(favorite.id) }

        binding.parent.setOnClickListener { onItemSelected(favorite) }
    }
}