package com.gaoyun.feature_random_advice

import com.gaoyun.advices.common.BaseViewModel
import com.gaoyun.advices.common.ext.ioToMain
import com.gaoyun.advices.domain.usecase.GetRandomAdviceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetRandomAdviceViewModel @Inject constructor(
    private val getRandomAdviceUseCase: GetRandomAdviceUseCase
) : BaseViewModel<GetRandomAdviceScreenContract.Event, GetRandomAdviceScreenContract.State, GetRandomAdviceScreenContract.Effect>() {

    init {
        getRandomAdvice()
    }

    /**
     * Sets initial state for GetRandomAdviceScreen
     */
    override fun setInitialState(): GetRandomAdviceScreenContract.State = GetRandomAdviceScreenContract.State(advice = null, isLoading = true)

    /**
     * Handles events from GetRandomAdviceScreen
     */
    override fun handleEvents(event: GetRandomAdviceScreenContract.Event) {
        when (event) {
            is GetRandomAdviceScreenContract.Event.GetNewAdvice -> {
                setState { copy(isLoading = true) }
                getRandomAdvice()
            }
        }
    }

    /**
     * Retrieves a random advice and sets state if retrieval is successful
     */
    fun getRandomAdvice() = disposables.add(
        getRandomAdviceUseCase.getRandomAdvice()
            .ioToMain()
            .subscribe(
                { setState { copy(advice = it, isLoading = false) }; setEffect { GetRandomAdviceScreenContract.Effect.AdviceLoaded } },
                { it.printStackTrace() }
            )
    )

}