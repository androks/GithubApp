package com.androks.githubapp.domain

import com.androks.githubapp.base.Result
import com.androks.githubapp.domain.model.RepositoryModel

interface RepositoriesRepository {
    suspend fun getRepositories(topic: String, limit: Int): Result<List<RepositoryModel>>
}