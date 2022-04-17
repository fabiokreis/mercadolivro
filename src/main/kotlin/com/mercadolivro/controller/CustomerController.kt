package com.mercadolivro.controller

import com.mercadolivro.model.CustomerModel
import com.mercadolivro.model.request.PostCustomerRequest
import com.mercadolivro.model.request.PutCustomerRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customer")
class CustomerController {

    val customers = mutableListOf<CustomerModel>()

    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerModel> {
        name?.let {
            return customers.filter { it.name.contains(name, true) }
        }

        return customers
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postCustomer(@RequestBody customer: PostCustomerRequest): PostCustomerRequest {
        val id = (if (customers.isEmpty()) 1 else customers.last().id.toInt() + 1).toString()
        val newCustomerModel = CustomerModel(id, customer.name, customer.email)
        customers.add(newCustomerModel)
        return customer
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: String): CustomerModel? = customers.firstOrNull { it.id == id }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: String, @RequestBody customer: PutCustomerRequest) {
        customers.firstOrNull { it.id == id }?.let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: String) {
        customers.removeIf { it.id == id }
    }

}