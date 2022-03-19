package com.gaoyun.advices.data.repository

import com.gaoyun.advices.data.remote.AdvicesSlipApiClient
import javax.inject.Inject

interface AdviceRepository {

}

class AdviceRepositoryImpl @Inject constructor(
    private val apiClient: AdvicesSlipApiClient
) : AdviceRepository {


}