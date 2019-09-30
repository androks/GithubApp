package com.androks.repositoriesfeature.data

import com.androks.repositoriesfeature.domain.RepositoriesRepository
import org.koin.dsl.module

val repositoriesDataModule = module {
    single<RepositoriesRepository> {
        object : RepositoriesRepository {
            override suspend fun getRepositories(): List<RepositoryModel>? {
                return listOf(
                    RepositoryModel().apply {
                        this.id = 23523L
                    }
                )
            }
        }
    }
}