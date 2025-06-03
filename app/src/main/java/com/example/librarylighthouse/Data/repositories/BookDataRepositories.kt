package com.example.librarylighthouse.Data.repositories

import com.example.librarylighthouse.Domain.repositories.BookDomainRepositories
import src.main.graphql.BooksQuery

class BookDataRepositories: BookDomainRepositories {
    override suspend fun getQueryBook(): Result<BooksQuery.Book> {
        TODO("Not yet implemented")
    }
}