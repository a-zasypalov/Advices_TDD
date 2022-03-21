package com.gaoyun.advices.data.model

import com.gaoyun.advices.domain.model.Advice
import io.reactivex.rxjava3.core.Single

/**
 * Advice slip object response
 *
 * @param slip - advice object
 */
data class AdviceSlipResponse(
    val slip: AdviceResponse
)

/**
 * Advice response object
 *
 * @param id - unique id of advice
 * @param advice - advice text
 */
data class AdviceResponse(
    val id: Long,
    val advice: String
)

/**
 * Mapping extension from Advice response to internal Advice object
 */
fun Single<AdviceResponse>.toSingleAdvice(): Single<Advice> = map { Advice(it.id, it.advice) }
