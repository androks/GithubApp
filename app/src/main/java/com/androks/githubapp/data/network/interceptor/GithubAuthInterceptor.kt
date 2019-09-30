package com.androks.githubapp.data.network.interceptor

import com.androks.githubapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class GithubAuthInterceptor : Interceptor {

    companion object {
        private const val AUTH = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()

        requestBuilder.addHeader(AUTH, "token ${BuildConfig.GITHUB_KEY}")

        return chain.proceed(requestBuilder.method(original.method, original.body).build())
    }
}
