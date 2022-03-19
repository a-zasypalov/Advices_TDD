package com.gaoyun.advices.domain.usecase

import com.gaoyun.advices.domain.repository.AdviceRepository
import javax.inject.Inject

class GetRandomAdviceUseCase @Inject constructor(private val repository: AdviceRepository) {

}