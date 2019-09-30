package com.androks.repositoriesfeature.presentation.list

import androidx.lifecycle.viewModelScope
import com.androks.base.presentation.BaseAction
import com.androks.base.presentation.BaseViewModel
import com.androks.base.presentation.BaseViewState
import com.androks.repositoriesfeature.data.RepositoryModel
import com.androks.repositoriesfeature.domain.RepositoriesRepository
import kotlinx.coroutines.launch

class RepositoryListViewModel(
    private val repositoryRepository: RepositoriesRepository
) : BaseViewModel<RepositoryListViewModel.ViewState, RepositoryListViewModel.Action>(ViewState()) {

    fun loadList() {
        viewModelScope.launch {
            repositoryRepository.getRepositories().also {
                if(it != null) {
                    sendAction(Action.ListLoadingSuccess(it))
                } else {
                    sendAction(Action.ListLoadingFailure)
                }
            }
        }
    }

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.ListLoadingSuccess -> ViewState(
            isLoading = false,
            isError = false,
            repositories = viewAction.repositories
        )
        is Action.ListLoadingFailure -> ViewState(
            isLoading = false,
            isError = true,
            repositories = emptyList()
        )
    }

    data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val repositories: List<RepositoryModel> = emptyList()
    ) : BaseViewState

    sealed class Action : BaseAction {
        class ListLoadingSuccess(val repositories: List<RepositoryModel>) : Action()
        object ListLoadingFailure : Action()
    }
}
