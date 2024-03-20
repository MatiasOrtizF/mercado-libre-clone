package com.mfo.mercadolibreclone.data.providers

import com.mfo.mercadolibreclone.domain.model.CategoryInfo
import javax.inject.Inject

class CategoryProvider @Inject constructor() {
    fun getCategories(): List<CategoryInfo> {
        return listOf(
            CategoryInfo.Vehicle,
            CategoryInfo.Agro,
            CategoryInfo.FoodAndBeverages,
            CategoryInfo.Animals,
            CategoryInfo.Collections,
            CategoryInfo.Art,
            CategoryInfo.Beauty,
            CategoryInfo.Cameras,
            CategoryInfo.CellPhones,
            CategoryInfo.Computers,
            CategoryInfo.ConsolesAndVideoGames,
            CategoryInfo.Construction,
            CategoryInfo.FitnessAndSports,
            CategoryInfo.Appliances,
            CategoryInfo.Electronics,
            CategoryInfo.Tools,
            CategoryInfo.HomeFurnitureAndGarden,
            CategoryInfo.RealEstate,
            CategoryInfo.Music,
            CategoryInfo.JewelryAndWatches,
            CategoryInfo.ToysAndGames,
            CategoryInfo.Books,
            CategoryInfo.MoviesAndSeries,
            CategoryInfo.ClothingAndAccessories,
            CategoryInfo.Health,
            CategoryInfo.Services,
            CategoryInfo.PartiesAndEvents,
            CategoryInfo.OtherCategories
        )
    }

}