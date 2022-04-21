package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {

    fun create(book: BookModel) {
        bookRepository.save(book)
    }

    fun findAll(pageable: Pageable): Page<BookModel> =
        bookRepository.findAll(pageable)

    fun findActives(pageable: Pageable): Page<BookModel> =
        bookRepository.findByStatus(BookStatus.ATIVO, pageable)

    fun findById(id: Int): BookModel? =
        bookRepository.findById(id).orElse(null)

    fun delete(id: Int) {
        val book = findById(id)

        book?.let {
            it.status = BookStatus.CANCELADO
            update(it)
        }
    }

    fun update(book: BookModel) {
        bookRepository.save(book)
    }

    fun deleteByCostumer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)

        books.forEach {
            it.status = BookStatus.DELETADO
        }

        bookRepository.saveAll(books)
    }

}
