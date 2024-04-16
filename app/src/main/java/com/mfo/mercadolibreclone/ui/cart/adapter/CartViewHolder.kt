package com.mfo.mercadolibreclone.ui.cart.adapter

import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfo.mercadolibreclone.data.network.response.CartResponse
import com.mfo.mercadolibreclone.databinding.ItemCartBinding

class CartViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemCartBinding.bind(view)

    fun bind(cart: CartResponse, onItemSelected: (CartResponse) -> Unit) {
        val context = binding.tvTitle.context
        binding.tvUserName.text = cart.product.user.name // change to username
        Glide.with(context).load(cart.product.image).into(binding.ivProduct)
        binding.tvTitle.text = cart.product.title
        binding.tvQuantity.text = cart.quantity.toString()
        binding.tvDiscount.text = if (cart.product.discountPercentage != null) "-${cart.product.discountPercentage}%" else null
        binding.tvOldPrice.text = if (cart.product.discountPercentage != null) "$ ${cart.product.price}" else null
        binding.tvOldPrice.paintFlags = binding.tvOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        val discountPrice = cart.product.price * (cart.product.discountPercentage?:0.0) / 100
        binding.tvPrice.text = "$ ${cart.product.price-discountPrice}"
        binding.tvShipment.text = cart.product.shipment?.toString() ?: "free"
        //binding.tvAgeKm.text = favorite.product.description

        binding.parent.setOnClickListener { onItemSelected(cart) }
    }
}