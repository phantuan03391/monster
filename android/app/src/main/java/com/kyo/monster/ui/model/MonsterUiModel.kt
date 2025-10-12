package com.kyo.monster.ui.model

/**
 * A dedicated, reusable model class for representing a Monster in the UI layer.
 * It lives in a common package so any screen can use it.
 */
data class MonsterUiModel(
    val id: String,
    val name: String,
    val hpText: String,
    val ratingText: String,
    val imageUrl: String
)
