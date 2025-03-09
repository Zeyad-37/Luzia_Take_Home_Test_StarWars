package com.luzia.architecture.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * View model that implements the MVI (Model View Intent) architecture pattern
 */
abstract class ViewModel<I : Input, R : Result, S : State, E : Effect>(
    initialState: S,
    private val reducer: Reducer<R, S>? = null,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) : ViewModel() {

    /**
     * [ViewModel] specific logger
     */
    private val tag: String = this::class.simpleName ?: "ViewModel"

    /**
     * Log uncaught exceptions in [resolve]
     */
    private val logUncaughtExceptions =
        CoroutineExceptionHandler { _, throwable -> Log.e(tag, "Uncaught exception", throwable) }

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)

    /**
     * Observe [state] in the view to react to state changes
     */
    val state: StateFlow<S> = _state

    private val _effect: MutableSharedFlow<E> = MutableSharedFlow()

    /**
     * Observe [effect] in the view to react to one time effects
     */
    val effect: Flow<E> = _effect

    /**
     * Send inputs to be processed by the view model, they will be handled by [resolve] and then the [Result]'s will
     * be passed to the relevant flow
     *
     * @param input the input to be handled
     */
    fun process(input: I) {
        log("Input", input)
        viewModelScope.launch(logUncaughtExceptions) {
            resolve(input, _state.value).onEach { result ->
                when (result) {
                    is Effect -> log("Effect", result).also { _effect.emit(result as E) }
                    is State -> _state.update { result as S }.also { log("State", it) }
                    else -> _state.update { state ->
                        log("Result", result)
                        reducer?.reduce(result as R, state)?.also { log("State", it) }
                            ?: throw NullPointerException("Reducer is null")
                    }
                }
            }.flowOn(dispatcher)
                .launchIn(viewModelScope)
        }
    }

    /**
     * Map [input]'s to [Result]'s
     *
     * @param state the current [State]
     * @param input the [Input] to be handled
     *
     * @return a [Flow] of [Result] representing the result of handling the input
     */
    protected abstract fun resolve(input: I, state: S): Flow<Result>

    /**
     * Log [Input]'s and the resulting [Result]'s, [Effect]'s and [State]
     */
    private fun log(type: String, item: Any) = Log.d(tag, "$type : $item")
}
