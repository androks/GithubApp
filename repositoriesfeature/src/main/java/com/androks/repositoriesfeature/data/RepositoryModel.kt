package com.androks.repositoriesfeature.data

class RepositoryModel {
    var id = 0L
    var name = ""
    var description = ""
    var language: String? = ""
    //var owner: OwnerRepositoryModel? = null

    //@SerializedName("stargazers_count")
    var stars = 0

    //@SerializedName("forks_count")
    var forks = 0
}