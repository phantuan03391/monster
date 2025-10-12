package com.kyo.monster.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyo.monster.domain.usecase.GetMonstersUseCase
import com.kyo.monster.ui.mapper.toUiModel
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
                .onSuccess { response ->
                    val uiMonsters = response.map { it.toUiModel() }
                    _homeUiState.update { it.copy(monsters = uiMonsters, isLoading = false) }
                }.onFailure { throwable ->
                    _homeUiState.update { it.copy(isLoading = false) }
                    Log.e("TAG", "getMonsters: ${throwable.message}")
                }
        }
    }
}
