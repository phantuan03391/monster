package com.example.di

import com.example.repository.IMonsterRepository
import com.example.repository.MonsterRepositoryImpl
import org.koin.dsl.module

val koinModule = module {
    single<IMonsterRepository> {
        MonsterRepositoryImpl()
    }
}