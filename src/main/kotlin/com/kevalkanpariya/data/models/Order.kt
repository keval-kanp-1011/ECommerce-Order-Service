package com.kevalkanpariya.data.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Order(
    val orderId: Int,
    val productId: Int,
    val orderBy: Int,
    val orderTo: Int,
    val transactionNo: String,
    val orderTimestamp: String,
    val orderAmt: String
)


object Orders: Table() {

    val orderId = integer("id").autoIncrement()
    val productId = integer("productId")
    val orderBy = integer("orderBy")
    val orderTo = integer("orderTo")
    val transactionNo = varchar("transactionNo", 10)
    val orderTimestamp = varchar("orderTimestamp", 20)
    val orderAmt = varchar("orderAmt", 10)

    override val primaryKey: PrimaryKey = PrimaryKey(orderId)
}