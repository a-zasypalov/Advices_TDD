package com.gaoyun.advices.domain.model


/**
 * Internal advice  object
 *
 * @param id - unique id of advice
 * @param advice - advice text
 */
data class Advice(
    val id: Long,
    val advice: String
)
