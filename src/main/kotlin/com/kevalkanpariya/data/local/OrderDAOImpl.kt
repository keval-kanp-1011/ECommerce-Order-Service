package com.kevalkanpariya.data.local

import com.kevalkanpariya.data.local.DatabaseSingleton.dbQuery
import com.kevalkanpariya.data.models.CreateOrderDetails
import com.kevalkanpariya.data.models.EditOrderDetails
import com.kevalkanpariya.data.models.Order
import com.kevalkanpariya.data.models.Orders
import org.jetbrains.exposed.sql.*

class OrderDAOImpl: OrderDAO {

    fun resultRowToOrder(row: ResultRow) = Order(
        orderId = row[Orders.orderId],
        productId = row[Orders.productId],
        orderBy = row[Orders.orderBy],
        orderTo = row[Orders.orderTo],
        orderAmt = row[Orders.orderAmt],
        orderTimestamp = row[Orders.orderTimestamp],
        transactionNo = row[Orders.transactionNo]
    )
    override suspend fun insertOrder(orderDetails: CreateOrderDetails): Order? = dbQuery{

        val insertStatement = Orders.insert {
            it[Orders.orderBy] = orderDetails.orderBy
            it[Orders.orderTo] = orderDetails.orderTo
            it[Orders.orderAmt] = orderDetails.orderAmt
            it[Orders.orderTimestamp] = orderDetails.orderTimestamp
            it[Orders.productId] = orderDetails.productId
            it[Orders.transactionNo] = orderDetails.transactionNo

        }


        val order = insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToOrder)

        order
    }

    override suspend fun editOrder(orderId: Int, orderDetails: EditOrderDetails): Boolean = dbQuery{
        Orders.update({Orders.orderId eq orderId}) {
            it[Orders.orderBy] = orderDetails.orderBy
            it[Orders.orderTo] = orderDetails.orderTo
            orderDetails.orderAmt?.let { amt ->
                it[Orders.orderAmt] = amt
            }

            orderDetails.orderTimestamp?.let {timestamp ->
                it[Orders.orderTimestamp] = timestamp
            }

            orderDetails.productId.let { productId ->
                it[Orders.productId] = productId
            }

            orderDetails.transactionNo?.let {traxNo ->
                it[Orders.transactionNo] = traxNo
            }


        } > 0
    }

    override suspend fun fetchOrder(orderId: Int): Order? = dbQuery{
        Orders.select { Orders.orderId eq orderId}
            .map(::resultRowToOrder)
            .singleOrNull()
    }

    override suspend fun fetchOrders(): List<Order> = dbQuery{
        Orders.selectAll()
            .map(::resultRowToOrder)
    }
}