package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerService {

    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customers.filter { it.name.contains(name, true) }
        }
        return customers
    }

    fun create(customer: CustomerModel) {
        val id = (if (customers.isEmpty()) 1 else customers.last().id?.toInt()?.let { it + 1 }).toString()
        customers.add(customer.copy(id = id))
    }

    fun getCustomer(id: String): CustomerModel? = customers.firstOrNull { it.id == id }

    fun update(customer: CustomerModel) {
        customers.firstOrNull { it.id == customer.id }?.let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun delete(id: String) {
        customers.removeIf { it.id == id }
    }
}