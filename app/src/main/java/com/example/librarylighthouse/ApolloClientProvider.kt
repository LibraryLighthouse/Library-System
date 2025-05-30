package com.example.librarylighthouse

import com.apollographql.apollo.ApolloClient

object ApolloClientProvider {
    val apolloClient = ApolloClient.Builder()
        .serverUrl("https://your-api-url.com/graphql")
        .build()
}