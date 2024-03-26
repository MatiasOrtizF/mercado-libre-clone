package com.mfo.mercadolibreclone.data.network.response

import android.media.session.MediaSession.Token
import com.google.gson.annotations.SerializedName
import com.mfo.mercadolibreclone.domain.model.User

data class LoginResponse (@SerializedName ("token") val token: String, @SerializedName("user") val user: User) {
    fun toDomain(): LoginResponse {
        return LoginResponse(
            token = token,
            user = user
        )
    }
}