package com.oakkub.survey.extensions

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

/**
 * Created by oakkub on 25/3/2018 AD.
 */
fun <T> LiveData<T>.observe(owner: LifecycleOwner, block: (T) -> Unit) {
    observe(owner, Observer {
        if (it != null) {
            block(it)
        }
    })
}