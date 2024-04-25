package com.kevalkanpariya.plugins

import com.kevalkanpariya.data.local.OrderDAOImpl
import com.kevalkanpariya.data.models.CreateOrderDetails
import com.kevalkanpariya.data.models.EditOrderDetails
import com.kevalkanpariya.data.models.Order
import com.kevalkanpariya.data.repoImpl.OrderRepoImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import kotlin.reflect.KClass
import kotlin.reflect.full.allSuperclasses

fun Application.configureRouting() {

    val orderDAO = OrderDAOImpl()
    val orderRepo = OrderRepoImpl(orderDAO)

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        authenticate {


            get("/orders") {
                val response = orderRepo.fetchOrders()

                println(response.data)
                call.respond(response.data, messageType = typeInfo<List<Order>>())
//                response.data?.let {
//                    println()
//
//                }?: kotlin.run {
//                    call.respond("response data is null")
//                }

            }

            get("/orders/{id}") {
                val orderId = call.parameters["id"]?.toIntOrNull()?: kotlin.run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }

                val response = orderRepo.fetchOrder(orderId)
                response.data?.let {
                    call.respond("${response.data}")
                }?: kotlin.run {
                    call.respond("response data is null")
                }

            }


            post("/create-order") {

                val orderDetails = runCatching {
                    call.receive<CreateOrderDetails>()

                }.getOrNull()?: kotlin.run {
                    call.respond(HttpStatusCode.OK)
                    call.respondText("${call.receive<CreateOrderDetails>()}")
                    return@post
                }


                val response = orderRepo.createOrder(orderDetails)

                call.respond(response.data?:"order has not been created")
            }


            put("/edit-order") {
                val orderID = call.parameters["id"]?.toIntOrNull()?: kotlin.run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@put
                }
                val orderDetails = call.receive<EditOrderDetails>()

                val response = orderRepo.editOrderDetails(orderID, orderDetails)

                call.respond(response.error?:"order has not been created")
            }

            put("/cancel-order") {

            }
        }
    }
}
