package com.mfo.mercadolibreclone.ui.myAccount

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
class MyAccountViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase): ViewModel(){

    private var _state = MutableStateFlow<MyAccountState>(MyAccountState.Loading)
    val state: StateFlow<MyAccountState> = _state

    fun getUser(token: String) {
        viewModelScope.launch {
            _state.value = MyAccountState.Loading
            try {
                val result = withContext(Dispatchers.IO) { getUserUseCase(token) }
                if(result != null) {
                    _state.value = MyAccountState.Success(result)
                } else {
                    _state.value = MyAccountState.Error("Error occurred, Please try again later.")
                }
            } catch (e: Exception) {
                val errorMessage: String = e.message.toString()
                _state.value = MyAccountState.Error(errorMessage)
            }
        }
    }
}