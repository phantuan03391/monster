package com.kyo.monster.data.model

import com.kyo.monster.domain.model.Monster
import com.kyo.monster.domain.model.MonsterRating
import com.kyo.monster.domain.model.MonsterType

data class MonsterDto(
    val id: String,
    val name: String,
    val hp: Int,
    val types: List<String>, // e.g., ["DRAGON", "EARTH"]
    val rate: Int,           // e.g., 1, 2, 3
    val image: String
)

/**
 * Maps a data-layer [MonsterDto] to a domain-layer [Monster] object.
 * This is where the business logic models are created from raw data.
 */
fun MonsterDto.toDomainModel(): Monster {
    return Monster(
        id = this.id,
        name = this.name,
        hp = this.hp,
        types = convertToType(this.types),
        rate = convertToRating(this.rate),
        image = this.image
    )
}

/**
 * Safely converts a list of type strings to a list of [MonsterType] enums.
 * Unknown types are ignored.
 */
private fun convertToType(types: List<String>): List<MonsterType> {
    return types.mapNotNull { typeString ->
        try {
            MonsterType.valueOf(typeString.uppercase())
        } catch (_: IllegalArgumentException) {
            println("Unknown monster type received: $typeString")
            null // If type is unknown, mapNotNull will discard it
        }
    }
}

/**
 * Safely converts an integer rating to a [MonsterRating] enum.
 * Unknown integer values default to NORMAL.
 */
private fun convertToRating(rate: Int): MonsterRating {
    return when (rate) {
        0 -> MonsterRating.GOOD
        1 -> MonsterRating.VERY_GOOD
        2 -> MonsterRating.EXCELLENT
        else -> MonsterRating.GOOD // Default case for safety
    }
}

