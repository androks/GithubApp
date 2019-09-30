package com.androks.githubapp.data.network

import com.androks.githubapp.SearchRepositoriesQuery
import com.androks.githubapp.base.Failure
import com.androks.githubapp.base.Result
import com.androks.githubapp.base.Success
import com.androks.githubapp.base.map
import com.androks.githubapp.domain.model.RepositoryModel
import com.androks.githubapp.data.network.mapper.mapSearchRepositoriesToListOfRepositoriesModel
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.toDeferred
import java.lang.Exception

class NetworkDataSourceImpl(
    private val apolloClient: ApolloClient
) : NetworkDataSource {

    override suspend fun getRepositories(topic: String, limit: Int): Result<List<RepositoryModel>> {
        return apolloClient.getResponse(SearchRepositoriesQuery("topic:$topic", limit))
            .map(::mapSearchRepositoriesToListOfRepositoriesModel)
    }

    private suspend fun <D : Operation.Data, T : Any, V : Operation.Variables> ApolloClient.getResponse(
        request: Query<D, T, V>
    ): Result<T> {
        return try {
            this.query(request).toDeferred().await().getResponse()
        } catch (e: Throwable) {
            Failure(Exception(e))
        }
    }

    private fun <T : Any> Response<T>.getResponse(): Result<T> {
        return try {
            if (hasErrors()) {
                Failure(errors()[0])
            }
            return data()?.let { Success(it) } ?: Failure(Exception("Data is null"))
        } catch (e: Exception) {
            Failure(e)
        }
    }
}
