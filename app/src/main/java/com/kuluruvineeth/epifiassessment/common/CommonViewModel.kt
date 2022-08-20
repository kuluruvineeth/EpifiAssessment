package com.kuluruvineeth.epifiassessment.common

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel

abstract class CommonViewModel() : ViewModel(), LifecycleObserver {

    override fun onCleared() {
        super.onCleared()
    }
}