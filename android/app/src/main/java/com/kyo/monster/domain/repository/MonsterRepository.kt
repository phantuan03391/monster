package com.kyo.monster.domain.repository

import com.kyo.monster.domain.model.Monster

interface MonsterRepository {
    suspend fun getMonsters(): Result<List<Monster>>
}
