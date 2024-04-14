package com.mfo.mercadolibreclone.ui.cart.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfo.mercadolibreclone.data.network.response.CartResponse
import com.mfo.mercadolibreclone.databinding.ItemCartBinding

class CartViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemCartBinding.bind(view)

    fun bind(cart: CartResponse, onItemSelected: (CartResponse) -> Unit) {
        val context = binding.tvTitle.context
        Glide.with(context).load(cart.product.image).into(binding.ivProduct)
        binding.tvTitle.text = cart.product.title
        binding.tvQuantity.text = cart.quantity.toString()
        binding.tvPrice.text = "$ ${cart.product.price}"
        //binding.tvAgeKm.text = favorite.product.description

        binding.parent.setOnClickListener { onItemSelected(cart) }
    }
}