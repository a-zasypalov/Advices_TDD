package com.gaoyun.feature_random_advice

import com.gaoyun.feature_random_advice.rule.RxSchedulerRule
import com.gaoyun.advices.domain.model.Advice
import com.gaoyun.advices.domain.usecase.GetRandomAdviceUseCase
import io.reactivex.rxjava3.core.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GetRandomAdviceViewModelTest {

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    private val getRandomAdviceUseCase = mock<GetRandomAdviceUseCase> {
        on {
            getRandomAdvice()
        } doReturn Single.just(Advice(1, "Test"))
    }

    private val viewModel = GetRandomAdviceViewModel(getRandomAdviceUseCase)

    @Test
    fun `getRandomAdvice should call GetRandomAdviceUseCase`() {
        viewModel.getRandomAdvice()

        verify(getRandomAdviceUseCase).getRandomAdvice()
    }

    @Test
    fun `initial viewModel state should be without data and loading==true`() {
        viewModel.setInitialState()

        assert(viewModel.viewState.value == GetRandomAdviceScreenContract.State(advice = null, isLoading = true))
    }

    @Test
    fun `getRandomAdvice should update viewModel state`() {
        viewModel.getRandomAdvice()

        assert(viewModel.viewState.value == GetRandomAdviceScreenContract.State(advice = Advice(1, "Test"), isLoading = false))
    }

}