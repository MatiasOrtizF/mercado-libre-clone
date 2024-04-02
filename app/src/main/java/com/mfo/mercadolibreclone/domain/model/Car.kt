package com.mfo.mercadolibreclone.domain.model

data class Car(
    val id: Long,
    val product: Product,
    val user: User,
    val brand: String,
    val model: String,
    val version: String,
    val kilometres: Int,
    val age: Int,
    val color: String,
    val doors: Int,
    val fuelType: String,
    val transmission: String
) {

    fun toDomain(): Car {
        return Car( id, product, user, brand, model, version, kilometres, age, color, doors, fuelType, transmission )
    }
}