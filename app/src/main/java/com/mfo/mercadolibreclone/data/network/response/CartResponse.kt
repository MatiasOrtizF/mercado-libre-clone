package com.mfo.mercadolibreclone.data.network.response

import com.google.gson.annotations.SerializedName
import com.mfo.mercadolibreclone.domain.model.Product

class CartResponse (@SerializedName("id") val id: Long, @SerializedName("quantity") val quantity: Int , @SerializedName("product") val product: Product) {
    fun toDomain(): CartResponse {
        return CartResponse(
            id = id,
            quantity = quantity,
            product = product
        )
    }
}