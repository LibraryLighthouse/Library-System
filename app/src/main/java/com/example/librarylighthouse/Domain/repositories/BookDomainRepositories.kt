package com.example.librarylighthouse.Domain.repositories

import src.main.graphql.BooksQuery

interface BookDomainRepositories {
    suspend fun getQueryBook(): Result<BooksQuery.Book>
}