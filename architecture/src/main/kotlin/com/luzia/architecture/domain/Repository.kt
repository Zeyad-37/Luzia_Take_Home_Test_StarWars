package com.luzia.architecture.domain

import kotlinx.coroutines.CoroutineDispatcher

interface Repository {

    val ioDispatcher: CoroutineDispatcher
}
