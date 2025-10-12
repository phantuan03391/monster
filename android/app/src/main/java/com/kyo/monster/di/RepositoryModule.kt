package com.kyo.monster.di

import com.kyo.monster.data.repository.MonsterRepositoryImpl
import com.kyo.monster.domain.repository.MonsterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindMonsterRepository(monsterRepositoryImpl: MonsterRepositoryImpl): MonsterRepository
}
