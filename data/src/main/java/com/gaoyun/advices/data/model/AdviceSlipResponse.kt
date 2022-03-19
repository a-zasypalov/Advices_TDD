package com.gaoyun.advices.data.model

import com.gaoyun.advices.domain.model.Advice
import io.reactivex.rxjava3.core.Single

data class AdviceSlipResponse(
    val slip: AdviceResponse
)

data class AdviceResponse(
    val id: Long,
    val advice: String
)

fun Single<AdviceResponse>.toSingleAdvice(): Single<Advice> = map { Advice(it.id, it.advice) }
