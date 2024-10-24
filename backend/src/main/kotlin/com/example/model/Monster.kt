package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Monster(
    val id: String,
    val name: String,
    val hp: Int,
    val type: List<MonsterType>,
    val rate: MonsterRating,
    val image: String
)

enum class MonsterType(val type: Int) {
    FLYING(1),
    DRAGON(2),
    FIRE(3),
    WATER(4),
    EARTH(5),
    ICE(6),
    DARK(7),
    LIGHT(8)
}

enum class MonsterRating(val rate: String) {
    POOR("poor"),
    AVERAGE("average"),
    GOOD("good"),
    VERY_GOOD("very_good"),
    EXCELLENT("excellent"),
    OUTSTANDING("outstanding"),
    LEGENDARY("legendary")
}