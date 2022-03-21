package com.gaoyun.advices.data.remote

import com.gaoyun.advices.data.model.AdviceSlipResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface AdvicesSlipApiClient {

    /**
     * Requests a random advice
     *
     * @return Single advice slip object
     */
    @GET("/advice")
    fun getRandomAdvice(): Single<AdviceSlipResponse>

}