package com.oakkub.survey.di

import com.oakkub.survey.data.local.oauth.OAuthLocalDataSource
import com.oakkub.survey.data.repository.oauth.OAuthRepository
import com.oakkub.survey.data.repository.oauth.OAuthRepositoryImpl
import com.oakkub.survey.data.repository.surveys.SurveysRepository
import com.oakkub.survey.data.repository.surveys.SurveysRepositoryImpl
import com.oakkub.survey.data.services.oauth.OAuthService
import com.oakkub.survey.data.services.surveys.SurveysService
import dagger.Module
import dagger.Provides

/**
 * Created by oakkub on 24/3/2018 AD.
 */
@Module
class RepositoryModule {

    @Provides
    fun provideOAuthRepository(oAuthService: OAuthService, oAuthLocalDataSource: OAuthLocalDataSource): OAuthRepository {
        return OAuthRepositoryImpl(oAuthService, oAuthLocalDataSource)
    }

    @Provides
    fun provideSurveysRepository(surveysService: SurveysService): SurveysRepository {
        return SurveysRepositoryImpl(surveysService)
    }

}