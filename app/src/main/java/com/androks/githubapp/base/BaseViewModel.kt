package com.androks.githubapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androks.githubapp.ext.toLiveData

abstract class BaseViewModel<ViewState : BaseViewState, ViewAction : BaseAction>(
    initialState: ViewState
) : ViewModel() {

    private var _state = MutableLiveData<ViewState>()
    val state = _state.toLiveData()

    init {
        _state.postValue(initialState)
    }

    fun sendAction(viewAction: ViewAction) = _state.postValue(onReduceState(viewAction))

    protected abstract fun onReduceState(viewAction: ViewAction): ViewState
}
