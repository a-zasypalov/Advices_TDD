package com.gaoyun.feature_random_advice

import com.gaoyun.advices.common.ViewEvent
import com.gaoyun.advices.common.ViewSideEffect
import com.gaoyun.advices.common.ViewState
import com.gaoyun.advices.domain.model.Advice

class GetRandomAdviceScreenContract {

    sealed class Event: ViewEvent {
        object GetNewAdvice : Event()
    }

    data class State(val advice: Advice? = null, val isLoading: Boolean = false) : ViewState

    sealed class Effect: ViewSideEffect {

        object AdviceLoaded : Effect()

        sealed class Navigation : Effect()

    }

}