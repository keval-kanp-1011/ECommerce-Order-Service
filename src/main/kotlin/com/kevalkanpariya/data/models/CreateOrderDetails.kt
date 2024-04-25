package com.kevalkanpariya.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderDetails(
    val productId: Int,
    val orderBy: Int,
    val orderTo: Int,
    val transactionNo: String,
    val orderTimestamp: String,
    val orderAmt: String
)


@Serializable
data class EditOrderDetails(
    val productId: Int,
    val orderBy: Int,
    val orderTo: Int,
    val transactionNo: String?=null,
    val orderTimestamp: String?=null,
    val orderAmt: String?=null
)
