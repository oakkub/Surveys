package com.oakkub.survey.di

import android.app.Application
import com.oakkub.survey.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by oakkub on 21/3/2018 AD.
 */
@Component(
        modules = [
            ActivityBinderModule::class,
            AndroidInjectionModule::class,
            AndroidSupportInjectionModule::class,
            AppModule::class,
            AuthenticationModule::class,
            FrescoModule::class,
            LocalModule::class,
            NetworkModule::class,
            RepositoryModule::class,
            RxModule::class,
            ViewModelModule::class
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
