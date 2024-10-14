package com.kyo.monster.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyo.monster.domain.repository.IHomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: IHomeRepository
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    fun getMonsters() {
        viewModelScope.launch {
            val result = homeRepository.getMonsters()
            result.onSuccess {
                _homeUiState.value = _homeUiState.value.copy(monsters = it)
            }.onFailure {
                Log.e("TAG", "getMonsters: ${it.message}")
            }
        }
    }
}
