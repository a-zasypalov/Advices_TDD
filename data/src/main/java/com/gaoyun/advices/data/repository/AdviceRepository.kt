package com.gaoyun.advices.data.repository

import com.gaoyun.advices.data.model.toSingleAdvice
import com.gaoyun.advices.data.remote.AdvicesSlipApiClient
import com.gaoyun.advices.domain.model.Advice
import com.gaoyun.advices.domain.repository.AdviceRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AdviceRepositoryImpl @Inject constructor(
    private val apiClient: AdvicesSlipApiClient
) : AdviceRepository {

    override fun getRandomAdvice(): Single<Advice> = apiClient.getRandomAdvice().map {
        it.slip
    }.toSingleAdvice()

}