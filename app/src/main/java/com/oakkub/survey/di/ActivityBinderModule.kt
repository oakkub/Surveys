package com.oakkub.survey.di

import com.oakkub.survey.di.screens.surveys.SurveysFragmentBinderModule
import com.oakkub.survey.ui.surveys.list.SurveysActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by oakkub on 22/3/2018 AD.
 */
@Module
abstract class ActivityBinderModule {

    @ContributesAndroidInjector(modules = [SurveysFragmentBinderModule::class])
    abstract fun bindSurveysActivity(): SurveysActivity

}