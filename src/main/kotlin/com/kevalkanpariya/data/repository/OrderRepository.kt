package com.kevalkanpariya.data.repository

import com.kevalkanpariya.data.models.CreateOrderDetails
import com.kevalkanpariya.data.models.EditOrderDetails
import com.kevalkanpariya.data.models.Order
import com.kevalkanpariya.util.BasicResponse

interface OrderRepository {

    suspend fun createOrder(
        orderDetails: CreateOrderDetails
    ): BasicResponse<Order>

    suspend fun editOrderDetails(
        orderId: Int,
        orderDetails: EditOrderDetails
    ): BasicResponse<Boolean>

    suspend fun fetchOrders(

    ): BasicResponse<List<Order>>

    suspend fun fetchOrder(
        orderId: Int
    ): BasicResponse<Order>

    suspend fun cancelOrder(
        orderId: String
    ): BasicResponse<Boolean>
}