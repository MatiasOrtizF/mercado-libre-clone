package com.mfo.mercadolibreclone.domain.usecase

import com.mfo.mercadolibreclone.data.network.response.UserResponse
import com.mfo.mercadolibreclone.domain.Repository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: Repository)  {
    suspend operator fun invoke(token: String): UserResponse? = repository.getUser(token)
}