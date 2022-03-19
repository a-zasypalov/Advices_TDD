package com.gaoyun.advices.domain.repository

import com.gaoyun.advices.domain.model.Advice
import io.reactivex.rxjava3.core.Single

interface AdviceRepository {

    fun getRandomAdvice(): Single<Advice>

}