package com.mfo.mercadolibreclone.data.network.response

import com.google.gson.annotations.SerializedName

class UserResponse (@SerializedName("name") val name: String, @SerializedName("lastName") val lastName: String, @SerializedName("email") val email: String) {
    fun toDomain(): UserResponse {
        return UserResponse(
            name = name,
            lastName = lastName,
            email = email
        )
    }
}