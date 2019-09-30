package com.androks.repositoriesfeature.presentation

import com.androks.repositoriesfeature.presentation.list.RepositoryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoriesPresentationModule = module {
    viewModel { RepositoryListViewModel(get()) }
}