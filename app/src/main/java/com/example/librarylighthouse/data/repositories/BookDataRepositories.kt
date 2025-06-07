package com.example.librarylighthouse.data.repositories

import com.example.librarylighthouse.domain.repositories.BookDomainRepositories
import src.main.graphql.BooksQuery

class BookDataRepositories: BookDomainRepositories {
    override suspend fun getQueryBook(): Result<BooksQuery.Book> {
        TODO("Not yet implemented")
    }
}