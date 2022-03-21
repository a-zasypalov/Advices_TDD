package com.gaoyun.advices.domain.repository

import com.gaoyun.advices.domain.model.Advice
import io.reactivex.rxjava3.core.Single

interface AdviceRepository {

    /**
     * Retrieves a random advice
     *
     * @return Single advice object
     */
    fun getRandomAdvice(): Single<Advice>

}