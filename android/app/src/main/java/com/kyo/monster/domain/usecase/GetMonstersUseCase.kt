package com.kyo.monster.domain.usecase

import com.kyo.monster.domain.model.Monster
import com.kyo.monster.domain.repository.MonsterRepository
import javax.inject.Inject

/**
 * A UseCase that encapsulates the business logic for fetching the list of monsters.
 *
 * This class has a single responsibility, making the architecture cleaner and more testable.
 */
class GetMonstersUseCase @Inject constructor(
    private val repository: MonsterRepository,
) {
    /**
     * By overriding the invoke operator, we can call this class instance as if it were a function.
     * For example: `getMonstersUseCase()`
     */
    suspend operator fun invoke(): Result<List<Monster>> {
        // Currently, the business logic is simple: just get the monsters from the repository.
        return repository.getMonsters()
    }
}
