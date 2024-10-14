package com.kyo.monster.data.repository

import com.kyo.monster.data.model.Monster
import com.kyo.monster.data.model.MonsterRating
import com.kyo.monster.data.model.MonsterType
import com.kyo.monster.domain.repository.IHomeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor() : IHomeRepository {
    override suspend fun getMonsters(): Result<List<Monster>> {
        val monsters = generateMonsters()
        return Result.success(monsters)
    }

    private fun generateMonsters(): List<Monster> {
        return listOf(
            Monster(
                id = "1",
                name = "Dragonite",
                hp = 100,
                type = listOf(MonsterType.DRAGON),
                rate = MonsterRating.VERY_GOOD
            ),
            Monster(
                id = "2",
                name = "Charizard",
                hp = 90,
                type = listOf(MonsterType.FIRE, MonsterType.DRAGON),
                rate = MonsterRating.EXCELLENT
            ), Monster(
                id = "3",
                name = "Blastoise",
                hp = 85,
                type = listOf(MonsterType.WATER),
                rate = MonsterRating.GOOD
            ), Monster(
                id = "4",
                name = "Venusaur",
                hp = 80,
                type = listOf(MonsterType.EARTH),
                rate = MonsterRating.GOOD
            ), Monster(
                id = "5",
                name = "Aerodactyl",
                hp = 75,
                type = listOf(MonsterType.FLYING, MonsterType.DRAGON),
                rate = MonsterRating.VERY_GOOD
            )
        )
    }
}
