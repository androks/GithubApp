package com.androks.githubapp

import android.app.Application
import com.androks.githubapp.data.repositoriesDataModule
import com.androks.githubapp.data.network.networkModule
import com.androks.githubapp.presentation.repositoriesPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(networkModule, repositoriesPresentationModule, repositoriesDataModule))
            androidContext(this@GithubApplication)
        }
    }
}