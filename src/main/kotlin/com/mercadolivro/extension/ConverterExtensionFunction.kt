package com.mercadolivro.extension

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.controller.response.CustomerResponse
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.BookModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel =
    CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO)

fun PutCustomerRequest.toCustomerModel(previousValue: CustomerModel): CustomerModel =
    CustomerModel(
        id = previousValue.id,
        name = this.name,
        email = this.email,
        status = previousValue.status
    )

fun CustomerModel.toResponse(): CustomerResponse =
    CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
    )

fun PostBookRequest.toBookModel(customer: CustomerModel?): BookModel =
    BookModel(
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer
    )

fun PutBookRequest.toBookModel(previousValue: BookModel): BookModel =
    BookModel(
        id = previousValue.id,
        name = this.name ?: previousValue.name,
        price = this.price ?: previousValue.price,
        status = previousValue.status,
        customer = previousValue.customer
    )

fun BookModel.toResponse(): BookResponse =
    BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        customer = this.customer,
        status = this.status
    )