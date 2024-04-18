package com.mfo.mercadolibreclone.ui.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfo.mercadolibreclone.R
import com.mfo.mercadolibreclone.data.network.response.CartResponse

class CartAdapter(private var productsList: MutableList<CartResponse> = mutableListOf(), private val onItemSelected:(CartResponse) -> Unit, private val onCartDeleteButtonClicked:(Long, Int) -> Unit, private val onFavoriteButtonClicked:(Long) -> Unit): RecyclerView.Adapter<CartViewHolder>() {
    fun updateList(list: MutableList<CartResponse>) {
        productsList = list
        notifyDataSetChanged()
    }

    fun onDeleteItem(position: Int) {
        productsList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun calculateTotalPriceShipping(): Pair<Double, Double> {
        var totalPrice = 0.0
        var totalShipping = 0.0
        for (cart in productsList) {

            val discountPrice = cart.product.price * (cart.product.discountPercentage?:0.0) / 100
            val newPrice = cart.product.price-discountPrice
            totalPrice += newPrice

            totalShipping += cart.product.shipment?:0.0
        }
        return Pair(totalPrice, totalShipping)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(productsList[position], onItemSelected, onCartDeleteButtonClicked, onFavoriteButtonClicked)
    }
}