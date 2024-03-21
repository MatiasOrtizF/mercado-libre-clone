package com.mfo.mercadolibreclone.ui.category

import androidx.lifecycle.ViewModel
import com.mfo.mercadolibreclone.data.providers.CategoryProvider
import com.mfo.mercadolibreclone.domain.model.CategoryInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(categoryProvider: CategoryProvider): ViewModel() {

    private var _category = MutableStateFlow<List<CategoryInfo>>(emptyList())
    val category: StateFlow<List<CategoryInfo>> = _category

    init {
        _category.value = categoryProvider.getCategories()
    }
}