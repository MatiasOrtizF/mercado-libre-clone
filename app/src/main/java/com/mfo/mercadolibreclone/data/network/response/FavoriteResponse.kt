package com.mfo.mercadolibreclone.data.network.response

import com.google.gson.annotations.SerializedName
import com.mfo.mercadolibreclone.domain.model.Product

class FavoriteResponse (@SerializedName ("id") val id: Long, @SerializedName("product") val product: Product) {
    fun toDomain(): FavoriteResponse {
        return FavoriteResponse(
            id = id,
            product = product
        )
    }
}