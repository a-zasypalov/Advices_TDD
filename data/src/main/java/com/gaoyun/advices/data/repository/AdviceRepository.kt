package com.gaoyun.advices.data.repository

import com.gaoyun.advices.data.model.AdviceResponse
import com.gaoyun.advices.data.remote.AdvicesSlipApiClient
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface AdviceRepository {

    fun getRandomAdvice(): Single<AdviceResponse>

}

class AdviceRepositoryImpl @Inject constructor(
    private val apiClient: AdvicesSlipApiClient
) : AdviceRepository {

    override fun getRandomAdvice(): Single<AdviceResponse> = apiClient.getRandomAdvice().map { it.slipResponse }

}