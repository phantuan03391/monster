package com.kyo.monster.ui.screen.home

import com.kyo.monster.data.model.Monster

data class HomeUiState(
    val isLoading: Boolean = false,
    val monsters: List<Monster> = emptyList()
)
