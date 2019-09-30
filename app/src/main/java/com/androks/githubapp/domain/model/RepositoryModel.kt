package com.androks.githubapp.domain.model

data class RepositoryModel(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var language: String? = "",
    var stars: Int = 0,
    var forks: Int = 0
)
