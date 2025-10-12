package com.kyo.monster.ui.mapper

import com.kyo.monster.domain.model.Monster
import com.kyo.monster.domain.model.MonsterRating
import com.kyo.monster.ui.model.MonsterUiModel

/**
 * Maps a domain [Monster] to a UI-specific [MonsterUiModel].
 * Lives in a common mapper package to be reusable.
 */
fun Monster.toUiModel(): MonsterUiModel {
    val ratingText = when (this.rate) {
        MonsterRating.GOOD -> "Good"
        MonsterRating.VERY_GOOD -> "Very Good"
        MonsterRating.EXCELLENT -> "Excellent"
    }
    return MonsterUiModel(
        id = this.id,
        name = this.name,
        hpText = this.hp.toString(),
        ratingText = ratingText,
        imageUrl = this.image
    )
}
