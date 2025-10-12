package com.kyo.monster.data.repository

import com.kyo.monster.data.model.MonsterDto
import com.kyo.monster.data.remote.MonsterRemoteDataSource
import com.kyo.monster.domain.model.MonsterRating
import com.kyo.monster.domain.model.MonsterType
import com.kyo.monster.domain.repository.MonsterRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExtendWith(MockKExtension::class)
class MonsterRepositoryTest {

    @MockK
    private lateinit var remoteDataSource: MonsterRemoteDataSource

    private lateinit var repository: MonsterRepository

    @BeforeEach
    fun setUp() {
        repository = MonsterRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `getMonsters() should return success with mapped list when remote source succeeds`() =
        runTest {
            // Arrange
            val fakeMonsterDtos = listOf(
                MonsterDto("1", "Terra", 100, listOf("DRAGON", "LIGHT"), 2, ""),
                MonsterDto("2", "Frost", 90, listOf("DRAGON", "ICE", "INVALID_TYPE"), 2, "")
            )
            coEvery { remoteDataSource.generateMonsters() } returns fakeMonsterDtos

            // Act
            val result = repository.getMonsters()

            // Assert
            assertTrue(result.isSuccess)
            val monsters = result.getOrNull()
            assertEquals(2, monsters?.size)

            // Verify the mapping logic was applied correctly
            val terra = monsters?.first()
            assertEquals("1", terra?.id)
            assertEquals("Terra", terra?.name)
            assertEquals(listOf(MonsterType.DRAGON, MonsterType.LIGHT), terra?.types)
            assertEquals(MonsterRating.EXCELLENT, terra?.rate)

            // Verify that invalid types are ignored during mapping
            val frost = monsters?.get(1)
            assertEquals(listOf(MonsterType.DRAGON, MonsterType.ICE), frost?.types)

            // Verify that the data source was called
            coVerify(exactly = 1) { remoteDataSource.generateMonsters() }
        }

    @Test
    fun `getMonsters() should return success with empty list when remote source returns empty`() =
        runTest {
            // Arrange
            coEvery { remoteDataSource.generateMonsters() } returns emptyList()

            // Act
            val result = repository.getMonsters()

            // Assert
            assertTrue(result.isSuccess)
            assertTrue(result.getOrNull()?.isEmpty() == true)
            coVerify(exactly = 1) { remoteDataSource.generateMonsters() }
        }

    @Test
    fun `getMonsters() should return failure when remote source throws exception`() = runTest {
        // Arrange
        val networkException = IOException("Network call failed")
        coEvery { remoteDataSource.generateMonsters() } throws networkException

        // Act
        val result = repository.getMonsters()

        // Assert
        assertTrue(result.isFailure)
        val capturedException = result.exceptionOrNull()
        assertTrue(capturedException is IOException)
        assertEquals("Network call failed", capturedException.message)

        // Verify
        coVerify(exactly = 1) { remoteDataSource.generateMonsters() }
    }
}
