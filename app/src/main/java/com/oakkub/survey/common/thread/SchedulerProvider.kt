package com.oakkub.survey.common.thread

import io.reactivex.Scheduler

/**
 * Created by oakkub on 24/3/2018 AD.
 */
interface SchedulerProvider {

    fun io(): Scheduler

    fun main(): Scheduler

}