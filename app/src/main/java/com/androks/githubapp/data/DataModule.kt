package com.androks.githubapp.data

import com.androks.githubapp.data.repository.RepositoriesRepositoryImpl
import com.androks.githubapp.domain.RepositoriesRepository
import org.koin.dsl.module

val repositoriesDataModule = module {
    single<RepositoriesRepository> { RepositoriesRepositoryImpl(get()) }
}