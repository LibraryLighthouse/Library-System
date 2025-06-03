package com.example.librarylighthouse.Data.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import com.example.librarylighthouse.Data.repositories.BookDataRepositories
import com.example.librarylighthouse.Domain.repositories.BookDomainRepositories
import dagger.Module
import okhttp3.OkHttpClient
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideApolloClient():ApolloClient{
        val okHttpClient = OkHttpClient.Builder().build()
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/")
            .okHttpClient(okHttpClient)
            .build()
        return apolloClient
    }

    @Provides
    fun provideCountryRepo(apolloClient: ApolloClient): BookDomainRepositories{
        return BookDataRepositories()
    }


}