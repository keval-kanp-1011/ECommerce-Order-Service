package com.kevalkanpariya.data.local

import com.kevalkanpariya.data.models.CreateOrderDetails
import com.kevalkanpariya.data.models.EditOrderDetails
import com.kevalkanpariya.data.models.Order

interface OrderDAO {

    suspend fun insertOrder(
        orderDetails: CreateOrderDetails
    ): Order?

    suspend fun editOrder(
        orderId: Int,
        orderDetails: EditOrderDetails
    ): Boolean

    suspend fun fetchOrder(
        orderId: Int
    ): Order?

    suspend fun fetchOrders(

    ): List<Order>

}