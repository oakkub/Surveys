package com.oakkub.survey.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by oakkub on 24/3/2018 AD.
 */
operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}