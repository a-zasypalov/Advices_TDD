package com.gaoyun.advices.data

import com.gaoyun.advices.data.model.AdviceResponse
import com.gaoyun.advices.data.model.AdviceSlipResponse
import com.gaoyun.advices.data.remote.AdvicesSlipApiClient
import com.gaoyun.advices.data.repository.AdviceRepositoryImpl
import com.gaoyun.advices.domain.model.Advice
import com.gaoyun.advices.domain.repository.AdviceRepository
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class AdviceRepositoryTest {

    private val adviceSlipApiClient = mock<AdvicesSlipApiClient> {
        on {
            getRandomAdvice()
        } doReturn Single.just(AdviceSlipResponse(AdviceResponse(1, "Test")))
    }

    private val adviceRepository: AdviceRepository = AdviceRepositoryImpl(adviceSlipApiClient)

    @Test
    fun `getRandomAdvice should call function in apiClient`() {
        adviceRepository.getRandomAdvice()
            .test()
            .assertNoErrors()
            .assertComplete()

        verify(adviceSlipApiClient).getRandomAdvice()
    }

    @Test
    fun `getRandomAdvice should return Advice`() {
        adviceRepository.getRandomAdvice()
            .test()
            .assertNoErrors()
            .assertComplete()
            .assertValue(Advice(1, "Test"))
    }

}