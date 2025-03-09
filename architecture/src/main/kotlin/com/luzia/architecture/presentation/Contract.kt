package com.luzia.architecture.presentation

/**
 * An [Input] represents an action that the view model can react to
 */
interface Input

/**
 * A [Result] represents the result of some action taken by the view model in response to an [Input]
 *
 * [Result]'s are reduced by a [Reducer] in order to update the [State]
 */
interface Result


/**
 * An [Effect] is a special type of [Result] that represents a one off action (e.g navigation)
 *
 * [Effect]'s are presented to the view immediately rather than being reduced
 */
interface Effect : Result

/**
 * A [State] represents the state of a screen
 */
interface State : Result
