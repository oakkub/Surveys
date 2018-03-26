package com.oakkub.survey.extensions

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by oakkub on 11/3/2017 AD.
 */
inline fun <reified T> Fragment.delegateTo(action: (T) -> Unit) {
    (targetFragment as? T)?.let {
        action(it)
        return
    }
    (parentFragment as? T)?.let {
        action(it)
        return
    }
    (activity as? T)?.let {
        action(it)
        return
    }
}

inline fun <reified T> AppCompatActivity.findFragmentByTagExt(tag: String): T? = supportFragmentManager.findFragmentByTag(tag) as? T
