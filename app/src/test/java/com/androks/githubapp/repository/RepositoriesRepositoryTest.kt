package com.androks.githubapp.repository

import com.androks.githubapp.CoroutineRule
import com.androks.githubapp.base.Success
import com.androks.githubapp.data.network.NetworkDataSource
import com.androks.githubapp.data.repository.RepositoriesRepositoryImpl
import com.androks.githubapp.domain.RepositoriesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepositoriesRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @MockK
    private lateinit var networkDataSource: NetworkDataSource

    private lateinit var repositoriesRepository: RepositoriesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repositoriesRepository = RepositoriesRepositoryImpl(networkDataSource)
    }

    @Test
    fun `test getRepositories called with right params`() {
        val topic = "android"
        val limit = 10

        val topicSlot = slot<String>()
        val limitSlot = slot<Int>()
        coEvery {
            networkDataSource.getRepositories(
                capture(topicSlot),
                capture(limitSlot)
            )
        } returns Success(emptyList())

        runBlocking {
            repositoriesRepository.getRepositories(topic, limit)
        }
        coVerify(exactly = 1) {
            networkDataSource.getRepositories(topicSlot.captured, limitSlot.captured)
        }
    }
}