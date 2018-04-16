package com.oakkub.survey.di

import com.oakkub.survey.common.thread.SchedulerProvider
import com.oakkub.survey.common.thread.SchedulerProviderImpl
import dagger.Module
import dagger.Provides

/**
 * Created by oakkub on 24/3/2018 AD.
 */
@Module
class RxModule {

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = SchedulerProviderImpl()

}
