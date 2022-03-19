package com.gaoyun.advices.data

import com.gaoyun.advices.data.model.AdviceResponse
import com.gaoyun.advices.data.model.AdviceSlipResponse
import com.gaoyun.advices.data.remote.AdvicesSlipApiClient
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class AdvicesSlipApiServiceTest {

    private val adviceSlipApiClient = mock<AdvicesSlipApiClient> {
        on {
            getRandomAdvice()
        } doReturn Single.just(AdviceSlipResponse(AdviceResponse(1, "Test")))
    }

    @Test
    fun `getRandomAdvice should return correct advice`() {
        adviceSlipApiClient.getRandomAdvice()
            .test()
            .assertNoErrors()
            .assertComplete()
            .assertValue(AdviceSlipResponse(AdviceResponse(1, "Test")))
    }

}