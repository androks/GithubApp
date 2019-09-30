package com.androks.githubapp.data.network

import okhttp3.Interceptor
import okhttp3.Response

class GithubAuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()

        requestBuilder.addHeader("Authorization", "token b829e13d5cacbfc13faf4d6fe58c6fd7cb05266b")

        return chain.proceed(requestBuilder.method(original.method, original.body).build())
    }
}
