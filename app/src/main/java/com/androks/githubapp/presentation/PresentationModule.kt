package com.androks.githubapp.presentation

import com.androks.githubapp.presentation.list.RepositoryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoriesPresentationModule = module {
    viewModel { RepositoryListViewModel(get()) }
}