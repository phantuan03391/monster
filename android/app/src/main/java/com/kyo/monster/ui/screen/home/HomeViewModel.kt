package com.kyo.monster.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyo.monster.domain.usecase.GetMonstersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMonstersUseCase: GetMonstersUseCase
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    fun getMonsters() {
        viewModelScope.launch {
            _homeUiState.update { it.copy(isLoading = true) }
            getMonstersUseCase()
                .onSuccess { monstersResponse ->
                    _homeUiState.update { it.copy(monsters = monstersResponse, isLoading = false) }
                }.onFailure {
                    _homeUiState.update { it.copy(isLoading = false) }
                    Log.e("TAG", "getMonsters: ${it.message}")
                }
        }
    }
}
