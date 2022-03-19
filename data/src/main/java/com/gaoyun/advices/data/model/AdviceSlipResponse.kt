package com.gaoyun.advices.data.model

import com.squareup.moshi.Json

data class AdviceSlipResponse(
    @field:Json(name = "slip")
    val slipResponse: AdviceResponse
)

data class AdviceResponse(
    @field:Json(name = "slip")
    val id: Long,

    @field:Json(name = "slip")
    val advice: String
)
