package com.androks.githubapp.data.network

import com.androks.githubapp.base.Result
import com.androks.githubapp.domain.model.RepositoryModel

interface NetworkDataSource {
    suspend fun getRepositories(topic: String, limit: Int): Result<List<RepositoryModel>>
}
