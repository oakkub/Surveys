package com.oakkub.survey.common.date

/**
 * Created by oakkub on 23/3/2018 AD.
 */
class TimestampGetterImplForTest(private val desiredTimestamp: Long) : TimestampGetter {
    override fun getCurrentTimestamp(): Long = desiredTimestamp
}