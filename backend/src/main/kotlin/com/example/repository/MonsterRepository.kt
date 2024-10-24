package com.example.repository

import com.example.model.ApiResponse
import com.example.model.Monster
import com.example.model.MonsterRating
import com.example.model.MonsterType

interface IMonsterRepository {
    suspend fun getAllMonsters(): ApiResponse
}

class MonsterRepositoryImpl: IMonsterRepository {
    override suspend fun getAllMonsters(): ApiResponse {
        return ApiResponse(
            success = true,
            message = "ok",
            monsters = generateMonsters()
        )
    }

    private fun generateMonsters(): List<Monster> {
        return listOf(
            Monster(
                id = "1",
                name = "Terra",
                hp = 100,
                type = listOf(MonsterType.DRAGON, MonsterType.EARTH),
                rate = MonsterRating.EXCELLENT,
                image = "https://firebasestorage.googleapis.com/v0/b/monster-7b6f3.appspot.com/o/monster-images%2Fimg_terra.jpg?alt=media&token=18ff1eab-9a78-4972-a01c-caa3c0d0e168"
            ), Monster(
                id = "2",
                name = "Frost",
                hp = 90,
                type = listOf(MonsterType.DRAGON, MonsterType.ICE),
                rate = MonsterRating.EXCELLENT,
                image = "https://firebasestorage.googleapis.com/v0/b/monster-7b6f3.appspot.com/o/monster-images%2Fimg_frost.jpg?alt=media&token=ea4a1475-0654-43f4-a147-325b37f03027"
            ), Monster(
                id = "3",
                name = "Blast",
                hp = 85,
                type = listOf(MonsterType.DRAGON, MonsterType.FIRE),
                rate = MonsterRating.GOOD,
                image = "https://firebasestorage.googleapis.com/v0/b/monster-7b6f3.appspot.com/o/monster-images%2Fimg_blast.jpg?alt=media&token=05026f8d-a181-4b80-b709-5f35fe4eb7a1"
            ), Monster(
                id = "4",
                name = "Shadow",
                hp = 100,
                type = listOf(MonsterType.DRAGON, MonsterType.DARK),
                rate = MonsterRating.EXCELLENT,
                image = "https://firebasestorage.googleapis.com/v0/b/monster-7b6f3.appspot.com/o/monster-images%2Fimg_shadow.jpg?alt=media&token=f5e7b7bd-5fe0-4a33-9ba9-c6695d3d6a01"
            ), Monster(
                id = "5",
                name = "Solara",
                hp = 100,
                type = listOf(MonsterType.DRAGON, MonsterType.LIGHT),
                rate = MonsterRating.EXCELLENT,
                image = "https://firebasestorage.googleapis.com/v0/b/monster-7b6f3.appspot.com/o/monster-images%2Fimg_solara.jpg?alt=media&token=aacd930b-7439-4232-9a6c-99b6ca18cb5e"
            ), Monster(
                id = "6",
                name = "Panda",
                hp = 75,
                type = listOf(MonsterType.EARTH),
                rate = MonsterRating.VERY_GOOD,
                image = "https://firebasestorage.googleapis.com/v0/b/monster-7b6f3.appspot.com/o/monster-images%2Fimg_panda.jpg?alt=media&token=04af4987-9301-44aa-a3f1-adf29cbc4919"
            ), Monster(
                id = "7",
                name = "Zephyr",
                hp = 75,
                type = listOf(MonsterType.FLYING),
                rate = MonsterRating.VERY_GOOD,
                image = "https://firebasestorage.googleapis.com/v0/b/monster-7b6f3.appspot.com/o/monster-images%2Fimg_zephyr.jpg?alt=media&token=aae8360f-7289-42ca-a275-888801e17e66"
            ), Monster(
                id = "8",
                name = "Abyssal",
                hp = 88,
                type = listOf(MonsterType.WATER, MonsterType.DARK),
                rate = MonsterRating.VERY_GOOD,
                image = "https://firebasestorage.googleapis.com/v0/b/monster-7b6f3.appspot.com/o/monster-images%2Fimg_abyssal.jpg?alt=media&token=d2c84e67-2e15-4421-960c-c534eedc589f"
            )
        )
    }
}