package com.kyo.monster.domain.repository

import com.kyo.monster.data.model.Monster

interface IHomeRepository {
    suspend fun getMonsters(): Result<List<Monster>>
}
