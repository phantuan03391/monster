package com.kyo.monster.data.repository

import com.kyo.monster.R
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
                name = "Terra",
                hp = 100,
                type = listOf(MonsterType.DRAGON, MonsterType.EARTH),
                rate = MonsterRating.EXCELLENT,
                image = R.drawable.img_terra
            ), Monster(
                id = "2",
                name = "Frost",
                hp = 90,
                type = listOf(MonsterType.DRAGON, MonsterType.ICE),
                rate = MonsterRating.EXCELLENT,
                image = R.drawable.img_frost
            ), Monster(
                id = "3",
                name = "Blast",
                hp = 85,
                type = listOf(MonsterType.DRAGON, MonsterType.FIRE),
                rate = MonsterRating.GOOD,
                image = R.drawable.img_blast
            ), Monster(
                id = "4",
                name = "Shadow",
                hp = 100,
                type = listOf(MonsterType.DRAGON, MonsterType.DARK),
                rate = MonsterRating.EXCELLENT,
                image = R.drawable.img_shadow
            ), Monster(
                id = "5",
                name = "Solara",
                hp = 100,
                type = listOf(MonsterType.DRAGON, MonsterType.LIGHT),
                rate = MonsterRating.EXCELLENT,
                image = R.drawable.img_solara
            ), Monster(
                id = "6",
                name = "Panda",
                hp = 75,
                type = listOf(MonsterType.EARTH),
                rate = MonsterRating.VERY_GOOD,
                image = R.drawable.img_panda
            ), Monster(
                id = "7",
                name = "Zephyr",
                hp = 75,
                type = listOf(MonsterType.FLYING),
                rate = MonsterRating.VERY_GOOD,
                image = R.drawable.img_zephyr
            ), Monster(
                id = "8",
                name = "Abyssal",
                hp = 88,
                type = listOf(MonsterType.WATER, MonsterType.DARK),
                rate = MonsterRating.VERY_GOOD,
                image = R.drawable.img_abyssal
            )
        )
    }
}
