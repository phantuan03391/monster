package com.kyo.monster.di

import com.kyo.monster.data.remote.MonsterRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideMonsterRemoteDataSource(): MonsterRemoteDataSource {
        return MonsterRemoteDataSource()
    }
}
