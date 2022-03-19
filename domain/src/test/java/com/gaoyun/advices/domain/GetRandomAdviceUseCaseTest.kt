package com.gaoyun.advices.domain

import com.gaoyun.advices.domain.model.Advice
import com.gaoyun.advices.domain.repository.AdviceRepository
import com.gaoyun.advices.domain.usecase.GetRandomAdviceUseCase
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GetRandomAdviceUseCaseTest {

    private val adviceRepository = mock<AdviceRepository> {
        on {
            getRandomAdvice()
        } doReturn Single.just(Advice(1, "Test"))
    }

    private val useCase = GetRandomAdviceUseCase(adviceRepository)

    @Test
    fun `getRandomAdvice should call AdviceRepository`() {
        useCase.getRandomAdvice()
            .test()
            .assertComplete()
            .assertNoErrors()

        verify(adviceRepository).getRandomAdvice()
    }

    @Test
    fun `getRandomAdvice should return correct advice`() {
        useCase.getRandomAdvice()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(Advice(1, "Test"))
    }

}