package com.oakkub.survey.di

import android.app.Application
import com.oakkub.survey.MainApplication
import dagger.BindsInstance
import dagger.Component

/**
 * Created by oakkub on 21/3/2018 AD.
 */
@Component(
        modules = [
            ActivityBinderModule::class,
            AppModule::class,
            NetworkModule::class,
            LocalModule::class,
            RepositoryModule::class,
            RxModule::class,
            AuthenticationModule::class
        ]
)
interface AppComponent {

    fun inject(application: MainApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

}