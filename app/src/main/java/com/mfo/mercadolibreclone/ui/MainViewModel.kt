package com.mfo.mercadolibreclone.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfo.mercadolibreclone.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase): ViewModel() {
    private var _state = MutableStateFlow<MainState>(MainState.Loading)
    val state: StateFlow<MainState> = _state

    fun getUser(token: String) {
        viewModelScope.launch {
            _state.value = MainState.Loading
            try {
                val result = withContext(Dispatchers.IO) { getUserUseCase(token) }
                if(result != null) {
                    _state.value = MainState.Success(result)
                } else {
                    _state.value = MainState.Error("Error occurred, Please try again later.")
                }
            } catch (e: Exception) {
                val errorMessage: String = e.message.toString()
                _state.value = MainState.Error(errorMessage)
            }
        }
    }
}