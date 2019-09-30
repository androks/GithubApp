package com.androks.repositoriesfeature.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.androks.base.presentation.ext.observe
import com.androks.repositoriesfeature.data.repositoriesDataModule
import com.androks.repositoriesfeature.presentation.repositoriesPresentationModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class RepositoryListFragment: Fragment() {

    private val repositoryViewModel: RepositoryListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(listOf(repositoriesPresentationModule, repositoriesDataModule))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(repositoryViewModel.state, ::showState)
    }

    private fun showState(state: RepositoryListViewModel.ViewState) {

    }
}
