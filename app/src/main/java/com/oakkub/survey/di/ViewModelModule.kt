package com.oakkub.survey.di

import android.arch.lifecycle.ViewModelProvider
import com.oakkub.survey.common.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Created by oakkub on 25/3/2018 AD.
 */
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}