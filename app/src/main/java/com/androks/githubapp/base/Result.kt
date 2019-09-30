package com.androks.githubapp.base

import com.apollographql.apollo.api.Error

sealed class Result<out T : Any>

class Failure(val message: Exception) : Result<Nothing>() {
    constructor(message: String) : this(RuntimeException(message))
    constructor(error: Error) : this(RuntimeException(error.message()))
    constructor() : this(RuntimeException("Unknown error"))
}

data class Success<out T : Any>(val value: T) : Result<T>()

fun <T : Any> Result<T>.ifSuccess(block: (T) -> Unit): Result<T> {
    if (this is Success) {
        block(value)
    }
    return this
}

fun <T : Any> Result<T>.ifError(block: (java.lang.Exception) -> Unit): Result<T> {
    if (this is Failure) {
        block(message)
    }
    return this
}

fun <T : Any, S : Any> Result<T>.map(block: (T) -> S): Result<S> {
    return when {
        this is Success -> return Success(block(value))
        this is Failure -> return this
        else -> Failure(RuntimeException())
    }
}
