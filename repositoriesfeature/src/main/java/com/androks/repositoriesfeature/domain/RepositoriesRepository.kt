package com.androks.repositoriesfeature.domain

import com.androks.repositoriesfeature.data.RepositoryModel

interface RepositoriesRepository {
    suspend fun getRepositories(): List<RepositoryModel>?
}