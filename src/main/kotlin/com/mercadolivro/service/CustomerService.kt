package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository
) {

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun create(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun getCustomer(id: Int): CustomerModel? = customerRepository.findById(id).orElse(null)

    fun update(customer: CustomerModel) {
        customer.id?.let {
            if (!customerRepository.existsById(it)) {
                throw Exception()
            }
            customerRepository.save(customer)
        }
    }

    fun delete(id: Int) {
        if (!customerRepository.existsById(id)) {
            throw Exception()
        }

        customerRepository.deleteById(id)
    }
}