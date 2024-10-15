package com.kyo.monster.data.model

import androidx.annotation.DrawableRes

data class Monster(
    val id: String,
    val name: String,
    val hp: Int,
    val type: List<MonsterType>,
    val rate: MonsterRating,
    @DrawableRes val image: Int
)

enum class MonsterType {
    FLYING,
    DRAGON,
    FIRE,
    WATER,
    EARTH,
    ICE,
    DARK,
    LIGHT
}

enum class MonsterRating {
    GOOD,
    VERY_GOOD,
    EXCELLENT
}
