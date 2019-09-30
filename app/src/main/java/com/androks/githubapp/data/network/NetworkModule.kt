package com.androks.githubapp.data.network

import com.androks.githubapp.data.network.GithubAuthInterceptor
import com.androks.githubapp.data.network.NetworkDataSource
import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(GithubAuthInterceptor())
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }
    single {
        ApolloClient.builder()
            .serverUrl("https://api.github.com/graphql")
            .okHttpClient(get())
            .build()
    }
    single { NetworkDataSource(get()) }
}
