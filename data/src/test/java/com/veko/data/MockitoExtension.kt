package com.veko.data

import kotlinx.coroutines.runBlocking
import org.mockito.BDDMockito

fun <T> givenSuspended(block: suspend () -> T) = BDDMockito.given(runBlocking { block() })

infix fun <T> BDDMockito.BDDMyOngoingStubbing<T>.willReturn(block: () -> T) = willReturn(block())