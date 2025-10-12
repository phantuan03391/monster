package com.kyo.monster.domain.usecase

import com.kyo.monster.domain.model.Monster
import com.kyo.monster.domain.model.MonsterRating
import com.kyo.monster.domain.model.MonsterType
import com.kyo.monster.domain.repository.IHomeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertFailsWith

/**
 * Unit tests for the [GetMonstersUseCase].
 *
 * This test class ensures that the use case correctly handles all possible outcomes
 * from the repository, verifying its role as a mediator between the data and presentation layers.
 */
@ExtendWith(MockKExtension::class)
class GetMonsterUseCaseTest {

    @MockK
    private lateinit var mockRepository: IHomeRepository

    private lateinit var getMonstersUseCase: GetMonstersUseCase

    @BeforeEach
    fun setUp() {
        getMonstersUseCase = GetMonstersUseCase(mockRepository)
    }

    @Test
    fun `invoke() should return success with monster list when repository succeeds`() = runTest {
        // Arrange
        val fakeMonsters = listOf(
            Monster("1", "Terra", 100, listOf(MonsterType.DRAGON), MonsterRating.EXCELLENT, "")
        )
        coEvery { mockRepository.getMonsters() } returns Result.success(fakeMonsters)

        // Act
        val result = getMonstersUseCase()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(fakeMonsters, result.getOrNull())

        // Verify
        coVerify(exactly = 1) { mockRepository.getMonsters() }
    }

    @Test
    fun `invoke() should return success with empty list when repository returns empty data`() =
        runTest {
            // Arrange
            coEvery { mockRepository.getMonsters() } returns Result.success(emptyList())

            // Act
            val result = getMonstersUseCase()

            // Assert
            assertTrue(result.isSuccess)
            assertTrue(result.getOrNull()?.isEmpty() == true)

            // Verify
            coVerify(exactly = 1) { mockRepository.getMonsters() }
        }

    @Test
    fun `invoke() should return failure with exception when repository fails`() = runTest {
        // Arrange
        val noInternetException = Exception("No internet connection")
        coEvery { mockRepository.getMonsters() } returns Result.failure(noInternetException)

        // Act
        val result = getMonstersUseCase()

        // Assert
        assertTrue(result.isFailure)
        assertEquals(noInternetException, result.exceptionOrNull())

        // Verify
        coVerify(exactly = 1) { mockRepository.getMonsters() }
    }

    @Test
    fun `invoke() should rethrow exception when repository throws an unexpected error`() = runTest {
        // Arrange
        val unexpectedException = RuntimeException("A critical, unexpected error!")
        coEvery { mockRepository.getMonsters() } throws unexpectedException

        // Act & Assert
        val thrownException = assertFailsWith<RuntimeException> {
            getMonstersUseCase()
        }
        assertEquals(unexpectedException.message, thrownException.message)

        // Verify
        coVerify(exactly = 1) { mockRepository.getMonsters() }
    }
}
