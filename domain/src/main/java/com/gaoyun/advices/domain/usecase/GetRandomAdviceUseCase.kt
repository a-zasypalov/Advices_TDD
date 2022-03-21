package com.gaoyun.advices.domain.usecase

import com.gaoyun.advices.domain.model.Advice
import com.gaoyun.advices.domain.repository.AdviceRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface GetRandomAdviceUseCase {

    /**
     * Retrieves a random advice
     *
     * @return Single advice object
     */
    fun getRandomAdvice(): Single<Advice>

}

class GetRandomAdviceUseCaseImpl @Inject constructor(private val repository: AdviceRepository) : GetRandomAdviceUseCase {

    /**
     * Retrieves a random advice
     *
     * @return Single advice object
     */
    override fun getRandomAdvice() = repository.getRandomAdvice()

}