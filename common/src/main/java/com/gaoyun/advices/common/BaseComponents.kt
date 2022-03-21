package com.gaoyun.advices.common

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject

const val LAUNCH_LISTEN_FOR_EFFECTS = "launch-listen-to-effects"

interface ViewState

interface ViewEvent

interface ViewSideEffect

/**
 * Base ViewModel for screens
 */
abstract class BaseViewModel<Event : ViewEvent, UiState : ViewState, Effect : ViewSideEffect> :
    ViewModel() {

    /**
     * Composite disposables for requests from VM
     */
    val disposables = CompositeDisposable()

    /**
     * Initial state of attached to VM screen
     */
    private val initialState: UiState by lazy { setInitialState() }
    abstract fun setInitialState(): UiState

    /**
     * Current state of attached to VM screen
     */
    private val _viewState: MutableState<UiState> = mutableStateOf(initialState)
    val viewState: State<UiState> = _viewState

    /**
     * Incoming event subject
     */
    private val _event: PublishSubject<Event> = PublishSubject.create()

    /**
     * Effects subject
     */
    private val _effect: PublishSubject<Effect> = PublishSubject.create()
    val effect: Observable<Effect> = _effect

    init {
        subscribeToEvents()
    }

    /**
     * Subscribing to incoming events
     */
    private fun subscribeToEvents() = disposables.add(_event.subscribe { handleEvents(it) })

    /**
     * Emit new event
     */
    fun setEvent(event: Event) = _event.onNext(event)

    /**
     * Event handler
     */
    abstract fun handleEvents(event: Event)

    /**
     * Set new screen state
     */
    protected fun setState(reducer: UiState.() -> UiState) {
        _viewState.value = viewState.value.reducer()
    }

    /**
     * Emit new effect
     */
    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        _effect.onNext(effectValue)
    }

    /**
     * OnCleared overriding to clear disposables
     */
    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}