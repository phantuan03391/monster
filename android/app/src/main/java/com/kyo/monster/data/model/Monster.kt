package com.kyo.monster.data.model

data class Monster(
    val id: String,
    val name: String,
    val hp: Int,
    val type: List<MonsterType>,
    val rate: MonsterRating
)

enum class MonsterType {
    FLYING,
    DRAGON,
    FIRE,
    WATER,
    EARTH
}

enum class MonsterRating {
    GOOD,
    VERY_GOOD,
    EXCELLENT
}
