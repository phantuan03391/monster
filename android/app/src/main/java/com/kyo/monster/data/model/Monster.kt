package com.kyo.monster.data.model

data class Monster(
    val id: String,
    val name: String,
    val hp: Int,
    val type: List<MonsterType>,
    val rate: MonsterRating,
    val image: String
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
