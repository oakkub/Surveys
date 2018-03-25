package com.oakkub.survey.di

import android.app.Application
import com.facebook.common.internal.Suppliers
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.facebook.imagepipeline.core.ImagePipelineConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

/**
 * Created by oakkub on 25/3/2018 AD.
 */
@Module
class FrescoModule {

    @Provides
    fun provideImagePipeline(application: Application, okHttpClient: OkHttpClient): ImagePipelineConfig {
        return OkHttpImagePipelineConfigFactory.newBuilder(application, okHttpClient)
                .experiment()
                .setBitmapPrepareToDraw(true, 0, Int.MAX_VALUE, true)
                .experiment()
                .setSmartResizingEnabled(Suppliers.BOOLEAN_TRUE)
                .build()
    }

}