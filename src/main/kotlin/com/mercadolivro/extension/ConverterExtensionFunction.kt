package com.mercadolivro.extension

import com.mercadolivro.model.CustomerModel
import com.mercadolivro.model.request.PostCustomerRequest
import com.mercadolivro.model.request.PutCustomerRequest

fun PostCustomerRequest.toCustomerModel(): CustomerModel =
    CustomerModel(name = this.name, email = this.email)

fun PutCustomerRequest.toCustomerModel(id: String): CustomerModel =
    CustomerModel(id = id, name = this.name, email = this.email)