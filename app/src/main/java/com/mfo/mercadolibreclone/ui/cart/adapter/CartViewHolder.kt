package com.mfo.mercadolibreclone.ui.cart.adapter

import android.graphics.Paint
import android.graphics.Typeface
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfo.mercadolibreclone.R
import com.mfo.mercadolibreclone.data.network.response.CartResponse
import com.mfo.mercadolibreclone.databinding.ItemCartBinding

class CartViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemCartBinding.bind(view)

    fun bind(cart: CartResponse, onItemSelected: (CartResponse) -> Unit, onCartDeleteButtonClicked: (Long, Int) -> Unit, onFavoriteButtonClicked: (Long) -> Unit) {
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
        binding.tvShipment.text = if (cart.product.shipment != null && cart.product.shipment !=  0.0) {
            "$ ${cart.product.shipment}"
        } else {
            val colorGreen = ContextCompat.getColor(context, R.color.green)
            binding.tvShipment.setTextColor(colorGreen)
            val textStyle = Typeface.create("sans-serif", Typeface.BOLD)
            binding.tvShipment.typeface = textStyle
            "free"
        }
        //binding.tvAgeKm.text = favorite.product.description

        binding.btnDeleteCart.setOnClickListener { onCartDeleteButtonClicked(cart.id, bindingAdapterPosition) }
        binding.btnSave.setOnClickListener { onFavoriteButtonClicked(cart.product.id) }

        binding.parent.setOnClickListener { onItemSelected(cart) }
    }
}