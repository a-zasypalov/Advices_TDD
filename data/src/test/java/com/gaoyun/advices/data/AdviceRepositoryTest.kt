package com.gaoyun.advices.data

import com.gaoyun.advices.data.model.AdviceResponse
import com.gaoyun.advices.data.model.AdviceSlipResponse
import com.gaoyun.advices.data.remote.AdvicesSlipApiClient
import com.gaoyun.advices.data.repository.AdviceRepository
import com.gaoyun.advices.data.repository.AdviceRepositoryImpl
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
    fun `getRandomAdvice should return AdviceResponse`() {
        adviceRepository.getRandomAdvice()
            .test()
            .assertNoErrors()
            .assertComplete()
            .assertValue(AdviceResponse(1, "Test"))
    }

}