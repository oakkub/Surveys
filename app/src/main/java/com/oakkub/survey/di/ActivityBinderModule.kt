package com.oakkub.survey.di

import com.oakkub.survey.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by oakkub on 22/3/2018 AD.
 */
@Module
abstract class ActivityBinderModule {

    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity

}