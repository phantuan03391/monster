package com.kyo.monster.ui.screen.home

import com.kyo.monster.ui.model.MonsterUiModel

data class HomeUiState(
    val isLoading: Boolean = false,
    val monsters: List<MonsterUiModel> = emptyList()
)
