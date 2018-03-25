package com.oakkub.survey.di.screens.surveys

import com.oakkub.survey.ui.surveys.SurveysFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by oakkub on 25/3/2018 AD.
 */
@Module
abstract class SurveysFragmentBinderModule {

    @ContributesAndroidInjector(modules = [SurveysFragmentModule::class, SurveysViewModelModule::class])
    abstract fun bindSurveysFragment(): SurveysFragment

}