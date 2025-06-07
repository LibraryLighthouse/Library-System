package com.example.librarylighthouse.domain.repositories

import src.main.graphql.BooksQuery

interface BookDomainRepositories {
    suspend fun getQueryBook(): Result<BooksQuery.Book>
}