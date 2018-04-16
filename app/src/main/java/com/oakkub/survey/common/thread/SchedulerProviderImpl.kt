package com.oakkub.survey.common.thread

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oakkub on 24/3/2018 AD.
 */
class SchedulerProviderImpl : SchedulerProvider {

    override fun io(): Scheduler = Schedulers.io()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()

}
