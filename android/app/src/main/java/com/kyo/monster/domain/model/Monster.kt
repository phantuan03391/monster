package com.kyo.monster.domain.model

data class Monster(
    val id: String,
    val name: String,
    val hp: Int,
    val types: List<MonsterType>,
    val rate: MonsterRating,
    val image: String
)

enum class MonsterType(value: String) {
    FLYING("flying"),
    DRAGON("dragon"),
    FIRE("fire"),
    WATER("water"),
    EARTH("earth"),
    ICE("ice"),
    DARK("dark"),
    LIGHT("light")
}

enum class MonsterRating(value: Int) {
    GOOD(0),
    VERY_GOOD(1),
    EXCELLENT(2)
}
