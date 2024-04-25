package com.kevalkanpariya.data.repoImpl

import com.kevalkanpariya.data.local.OrderDAO
import com.kevalkanpariya.data.models.CreateOrderDetails
import com.kevalkanpariya.data.models.EditOrderDetails
import com.kevalkanpariya.data.models.Order
import com.kevalkanpariya.data.repository.OrderRepository
import com.kevalkanpariya.util.BasicResponse

class OrderRepoImpl(
    private val orderDAO: OrderDAO
): OrderRepository {
    override suspend fun createOrder(orderDetails: CreateOrderDetails): BasicResponse<Order> {
        return try {

            println("order creation is started")
            val order = orderDAO.insertOrder(orderDetails)

            println("order created is ended:: $order")
            BasicResponse.Success(data = order)
        }catch (e: Exception) {
            BasicResponse.Error(e.message?: "something went wrong")
        }
    }

    override suspend fun editOrderDetails(orderId: Int, orderDetails: EditOrderDetails): BasicResponse<Boolean> {
        return try {

            val isOrderEdited = orderDAO.editOrder(orderId, orderDetails)
            BasicResponse.Success(data = isOrderEdited)
        }catch (e: Exception) {
            BasicResponse.Error(e.message?: "something went wrong")
        }
    }

    override suspend fun fetchOrders(): BasicResponse<List<Order>> {
        return try {

            val orders = orderDAO.fetchOrders()
            BasicResponse.Success(data = orders)
        }catch (e: Exception) {
            BasicResponse.Error(e.message?: "something went wrong")
        }
    }

    override suspend fun fetchOrder(orderId: Int): BasicResponse<Order> {
        return try {
            val order = orderDAO.fetchOrder(orderId)
            BasicResponse.Success(data = order)
        }catch (e: Exception) {
            BasicResponse.Error(e.message?: "something went wrong")
        }
    }

    override suspend fun cancelOrder(orderId: String): BasicResponse<Boolean> {
        return try {

            BasicResponse.Success(data = null)
        }catch (e: Exception) {
            BasicResponse.Error(e.message?: "something went wrong")
        }
    }


}