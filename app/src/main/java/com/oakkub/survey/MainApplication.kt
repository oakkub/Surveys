package com.oakkub.survey

import android.app.Activity
import android.app.Application
import com.oakkub.survey.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by oakkub on 21/3/2018 AD.
 */
class MainApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjection: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjection
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

}