package com.oakkub.survey.di.screens.surveys

import android.arch.lifecycle.ViewModel
import com.oakkub.survey.common.annotations.ViewModelKey
import com.oakkub.survey.ui.surveys.list.SurveysViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by oakkub on 25/3/2018 AD.
 */
@Module
abstract class SurveysViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SurveysViewModel::class)
    abstract fun provideSurveysViewModel(surveysViewModel: SurveysViewModel): ViewModel

}
