package com.gaoyun.feature_random_advice

import com.gaoyun.advices.common.BaseViewModel
import com.gaoyun.advices.domain.usecase.GetRandomAdviceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetRandomAdviceViewModel @Inject constructor(
    private val getRandomAdviceUseCase: GetRandomAdviceUseCase
) : BaseViewModel<GetRandomAdviceScreenContract.Event, GetRandomAdviceScreenContract.State, GetRandomAdviceScreenContract.Effect>() {

    override fun setInitialState(): GetRandomAdviceScreenContract.State {
        TODO("Not yet implemented")
    }

    override fun handleEvents(event: GetRandomAdviceScreenContract.Event) {
        TODO("Not yet implemented")
    }

}