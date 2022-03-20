package com.gaoyun.advices.common

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject

interface ViewState

interface ViewEvent

interface ViewSideEffect

abstract class BaseViewModel<Event : ViewEvent, UiState : ViewState, Effect : ViewSideEffect> :
    ViewModel() {

    val disposables = CompositeDisposable()

    private val initialState: UiState by lazy { setInitialState() }
    abstract fun setInitialState(): UiState

    private val _viewState: MutableState<UiState> = mutableStateOf(initialState)
    val viewState: State<UiState> = _viewState

    private val _event: PublishSubject<Event> = PublishSubject.create()

    private val _effect: PublishSubject<Effect> = PublishSubject.create()
    val effect: Observable<Effect> = _effect

    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents() = disposables.add(_event.subscribe { handleEvents(it) })

    fun setEvent(event: Event) = _event.onNext(event)

    abstract fun handleEvents(event: Event)

    protected fun setState(reducer: UiState.() -> UiState) {
        _viewState.value = viewState.value.reducer()
    }

    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        _effect.onNext(effectValue)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}