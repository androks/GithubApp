package com.androks.githubapp.data.repository

import com.androks.githubapp.base.Result
import com.androks.githubapp.domain.model.RepositoryModel
import com.androks.githubapp.data.network.NetworkDataSource
import com.androks.githubapp.domain.RepositoriesRepository

class RepositoriesRepositoryImpl(
    private val networkDataSource: NetworkDataSource
) : RepositoriesRepository {
    override suspend fun getRepositories(): Result<List<RepositoryModel>> {
        return networkDataSource.getRepositories()
    }
}
