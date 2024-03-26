package com.mfo.mercadolibreclone.domain.usecase

import com.mfo.mercadolibreclone.data.network.response.LoginResponse
import com.mfo.mercadolibreclone.domain.Repository
import com.mfo.mercadolibreclone.domain.model.LoginRequest
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(loginRequest: LoginRequest): LoginResponse? = repository.authenticationUser(loginRequest)
}