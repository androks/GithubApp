package com.androks.githubapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.androks.githubapp.CoroutineRule
import com.androks.githubapp.base.Failure
import com.androks.githubapp.base.Success
import com.androks.githubapp.domain.model.RepositoryModel
import com.androks.githubapp.domain.RepositoriesRepository
import com.androks.githubapp.presentation.list.RepositoryListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepositoriesViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var repositoriesRepository: RepositoriesRepository

    private lateinit var repositoriesViewModel: RepositoryListViewModel

    init {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Before
    fun setUp() {
        repositoriesViewModel = RepositoryListViewModel(repositoriesRepository)
    }

    @Test
    fun `execute getRepositories`() {
        coEvery { repositoriesRepository.getRepositories() } returns Success(emptyList())

        repositoriesViewModel.loadList()

        coVerify(exactly = 1) { repositoriesRepository.getRepositories() }
    }

    @Test
    fun `right state on viewModel init`() {
        assertEquals(
            RepositoryListViewModel.ViewState(
                isError = false,
                isLoading = true,
                repositories = emptyList()
            ),
            repositoriesViewModel.state.value
        )
    }

    @Test
    fun `right state when getRepositories empty`() {
        coEvery { repositoriesRepository.getRepositories() } returns Success(emptyList())

        repositoriesViewModel.loadList()

        assertEquals(
            RepositoryListViewModel.ViewState(
                isError = false,
                isLoading = false,
                repositories = emptyList()
            ),
            repositoriesViewModel.state.value
        )
    }

    @Test
    fun `right state when getRepositories returns list`() {
        val repositories =
            listOf(RepositoryModel().apply { id = 123 }, RepositoryModel().apply { id = 352 })
        coEvery { repositoriesRepository.getRepositories() } returns Success(repositories)

        repositoriesViewModel.loadList()

        assertEquals(
            RepositoryListViewModel.ViewState(
                isError = false,
                isLoading = false,
                repositories = repositories
            ),
            repositoriesViewModel.state.value
        )
    }

    @Test
    fun `right state when getRepositories throw Exception`() {
        coEvery { repositoriesRepository.getRepositories() } returns Failure()

        repositoriesViewModel.loadList()

        assertEquals(
            RepositoryListViewModel.ViewState(
                isError = true,
                isLoading = false,
                repositories = emptyList()
            ),
            repositoriesViewModel.state.value
        )
    }
}
