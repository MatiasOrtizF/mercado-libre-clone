package com.mfo.mercadolibreclone.ui.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfo.mercadolibreclone.R
import com.mfo.mercadolibreclone.data.network.response.FavoriteResponse

class FavoriteAdapter(private var productsList: List<FavoriteResponse> = emptyList(), private val onItemSelected:(FavoriteResponse) -> Unit, private val onFavoriteDeleteButtonClicked:(Long) -> Unit): RecyclerView.Adapter<FavoriteViewHolder>() {
    fun updateList(list: List<FavoriteResponse>) {
        productsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(productsList[position], onItemSelected, onFavoriteDeleteButtonClicked)
    }

}