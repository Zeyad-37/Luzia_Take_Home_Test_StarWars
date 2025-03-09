package com.luzia.architecture

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val myDispatcher: MyDispatchers)

enum class MyDispatchers {
    Default,
    IO,
}
