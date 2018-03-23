package com.oakkub.survey.common.date

/**
 * Created by oakkub on 23/3/2018 AD.
 */
class TimestampGetterImpl : TimestampGetter {
    override fun getCurrentTimestamp(): Long = System.currentTimeMillis()
}