package com.oakkub.survey.ext

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

/**
 * Created by oakkub on 24/3/2018 AD.
 */
fun <T> whenever(methodCall: T?): OngoingStubbing<T> = Mockito.`when`(methodCall)

inline infix fun <T> OngoingStubbing<T>.then(block: () -> T): OngoingStubbing<T> = thenReturn(block())