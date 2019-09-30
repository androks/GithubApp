package com.androks.githubapp.data.network.mapper

import com.androks.githubapp.SearchRepositoriesQuery
import com.androks.githubapp.domain.model.RepositoryModel
import com.androks.githubapp.fragment.BaseRepository

fun mapSearchRepositoriesToListOfRepositoriesModel(input: SearchRepositoriesQuery.Data): List<RepositoryModel> {
    return input
        .search()
        .nodes()
        ?.map { it.fragments().baseRepository() }
        ?.map { mapBaseRepositoryToRepositoryModel(it) }
        ?: emptyList()
}

private fun mapBaseRepositoryToRepositoryModel(baseRepository: BaseRepository?): RepositoryModel {
    baseRepository ?: return RepositoryModel()
    return RepositoryModel(
        id = baseRepository.id(),
        name = baseRepository.name(),
        description = baseRepository.description() ?: "",
        forks = baseRepository.forkCount(),
        language = baseRepository.primaryLanguage()?.name(),
        stars = baseRepository.stargazers().totalCount()
    )
}
