package com.eggdigital.trueyouedc.extensions.views

import android.app.Activity
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by oakkub on 10/10/2017 AD.
 */
fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(context, message, duration).show()
}