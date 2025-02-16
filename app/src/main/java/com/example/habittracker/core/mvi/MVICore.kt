package com.example.habittracker.core.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Action
 *
 * @constructor Create empty Action
 */
interface Action

/**
 * State
 *
 * @constructor Create empty State
 */
interface State

/**
 * View effect
 *
 * @constructor Create empty View effect
 */
interface ViewEffect

/**
 * Reducer
 *
 * @param S
 * @param A
 * @constructor Create empty Reducer
 */
interface Reducer<S : State, A : Action, VE : ViewEffect> {
    fun reduce(
        currentState: S,
        action: A,
    ): Pair<S, VE?>
}

/**
 * Store
 *
 * @param S
 * @param A
 * @property reducer
 * @constructor
 *
 * @param initialState
 */
class Store<S : State, A : Action, VE : ViewEffect>(
    initialState: S,
    private val reducer: Reducer<S, A, VE>,
    private val scope: CoroutineScope,
) {
    /**
     * handle ui state
     */
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    /**
     * handle view effect and navigation
     */
    private val _effect = MutableSharedFlow<VE>()
    val effect: SharedFlow<VE> = _effect.asSharedFlow()

    fun dispatch(action: A) {
        val currentState = _state.value
        val pair = reducer.reduce(currentState, action)
//        Timber.tag("Store").i("dispatch() --> newState: $pair")
        _state.value = pair.first
//        Timber.tag("Store").i("dispatch() --> effect: ${pair.second}")
        scope.launch {
            pair.second?.let { _effect.emit(it) }
        }
    }
}
