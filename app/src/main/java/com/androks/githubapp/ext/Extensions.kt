package com.androks.githubapp.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

fun <T> MutableLiveData<T>.toLiveData() = this as LiveData<T>

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, body: (T) -> Unit = {}) {
    liveData.observe(this, Observer {
        it ?: return@Observer
        body(it)
    })
}
