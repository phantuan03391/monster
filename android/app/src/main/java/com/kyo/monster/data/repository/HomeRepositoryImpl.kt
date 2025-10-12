package com.kyo.monster.data.repository

import com.kyo.monster.data.model.toDomainModel
import com.kyo.monster.data.remote.MonsterRemoteDataSource
import com.kyo.monster.domain.model.Monster
import com.kyo.monster.domain.repository.IHomeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: MonsterRemoteDataSource
) : IHomeRepository {
    override suspend fun getMonsters(): Result<List<Monster>> {
        return try {
            val monsters = remoteDataSource.generateMonsters().map { it.toDomainModel() }
            Result.success(monsters)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
