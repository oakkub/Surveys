package com.oakkub.survey.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.oakkub.survey.common.date.TimestampGetter
import com.oakkub.survey.common.date.TimestampGetterImpl
import dagger.Module
import dagger.Provides

/**
 * Created by oakkub on 21/3/2018 AD.
 */
@Module
class AppModule {

    @Provides
    fun provideContext(application: Application): Context = application

    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

    @Provides
    fun provideTimestampGetter(): TimestampGetter = TimestampGetterImpl()

}