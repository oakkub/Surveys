package com.oakkub.survey

import android.app.Application
import com.oakkub.survey.di.DaggerAppComponent

/**
 * Created by oakkub on 21/3/2018 AD.
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

}