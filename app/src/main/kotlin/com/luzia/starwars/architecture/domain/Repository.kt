package com.luzia.starwars.architecture.domain

import kotlinx.coroutines.CoroutineDispatcher

interface Repository {

    val ioDispatcher: CoroutineDispatcher
}
