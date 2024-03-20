package com.mfo.mercadolibreclone.domain.model

import com.mfo.mercadolibreclone.R

sealed class CategoryInfo(val img: Int, val name: Int) {
    object Vehicle : CategoryInfo(R.drawable.ic_vehicle, R.string.tv_vehicles)

    object Agro : CategoryInfo(R.drawable.ic_agro, R.string.tv_agro)

    object FoodAndBeverages : CategoryInfo(R.drawable.ic_food, R.string.tv_food_and_beverages)

    object Animals : CategoryInfo(R.drawable.ic_animals, R.string.tv_animals)

    object Collections : CategoryInfo(R.drawable.ic_collections, R.string.tv_collections)

    object Art : CategoryInfo(R.drawable.ic_art, R.string.tv_art)

    object Beauty : CategoryInfo(R.drawable.ic_beauty, R.string.tv_beauty)

    object Cameras : CategoryInfo(R.drawable.ic_cameras, R.string.tv_cameras)

    object CellPhones : CategoryInfo(R.drawable.ic_cellphones, R.string.tv_cell_phones)

    object Computers : CategoryInfo(R.drawable.ic_computers, R.string.tv_computers)

    object ConsolesAndVideoGames : CategoryInfo(R.drawable.ic_consoles, R.string.tv_consoles_and_video_games)

    object Construction : CategoryInfo(R.drawable.ic_construction, R.string.tv_construction)

    object FitnessAndSports : CategoryInfo(R.drawable.ic_fitness, R.string.tv_fitness_and_sports)

    object Appliances : CategoryInfo(R.drawable.ic_appliances, R.string.tv_appliances)

    object Electronics : CategoryInfo(R.drawable.ic_electronics, R.string.tv_electronics)

    object Tools : CategoryInfo(R.drawable.ic_tools, R.string.tv_tools)

    object HomeFurnitureAndGarden : CategoryInfo(R.drawable.ic_home_furniture, R.string.tv_home_furniture_and_garden)

    object RealEstate : CategoryInfo(R.drawable.ic_real_estate, R.string.tv_real_estate)

    object Music : CategoryInfo(R.drawable.ic_music, R.string.tv_music)

    object JewelryAndWatches : CategoryInfo(R.drawable.ic_jewelry, R.string.tv_jewelry_and_watches)

    object ToysAndGames : CategoryInfo(R.drawable.ic_toys, R.string.tv_toys_and_games)

    object Books : CategoryInfo(R.drawable.ic_books, R.string.tv_books)

    object MoviesAndSeries : CategoryInfo(R.drawable.ic_movies, R.string.tv_movies_and_series)

    object ClothingAndAccessories : CategoryInfo(R.drawable.ic_clothing, R.string.tv_clothing_and_accessories)

    object Health : CategoryInfo(R.drawable.ic_health, R.string.tv_health)

    object Services : CategoryInfo(R.drawable.ic_services, R.string.tv_services)

    object PartiesAndEvents : CategoryInfo(R.drawable.ic_parties, R.string.tv_parties_and_events)

    object OtherCategories : CategoryInfo(R.drawable.ic_other, R.string.tv_other_categories)

}