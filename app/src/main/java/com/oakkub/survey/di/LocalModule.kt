package com.oakkub.survey.di

import android.content.SharedPreferences
import com.oakkub.survey.data.local.oauth.OAuthLocalDataSource
import com.oakkub.survey.data.local.oauth.OAuthLocalDataSourceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by oakkub on 23/3/2018 AD.
 */
@Module
class LocalModule {

    @Provides
    fun provideOAuthLocalDataSource(sharedPreferences: SharedPreferences): OAuthLocalDataSource {
        return OAuthLocalDataSourceImpl(sharedPreferences)
    }

}