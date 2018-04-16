package com.oakkub.survey.common.date

import java.util.concurrent.TimeUnit

/**
 * Created by oakkub on 23/3/2018 AD.
 */
class TimestampGetterImpl : TimestampGetter {
    override fun getCurrentTimestamp(): Long = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
}
