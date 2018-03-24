package com.oakkub.survey.di

import com.oakkub.survey.data.services.oauth.OAuthRequest
import dagger.Module
import dagger.Provides

/**
 * Created by oakkub on 24/3/2018 AD.
 */
@Module
class AuthenticationModule {

    @Provides
    fun provideOAuthRequest(): OAuthRequest = OAuthRequest("password", "carlos@nimbl3.com", "antikera")

}