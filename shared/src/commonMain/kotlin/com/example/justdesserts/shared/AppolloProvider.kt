package com.example.justdesserts.shared

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.interceptor.BearerTokenInterceptor
import com.apollographql.apollo.interceptor.TokenProvider
import com.apollographql.apollo.network.http.ApolloHttpNetworkTransport
import com.example.justdesserts.shared.cache.Database
import com.example.justdesserts.shared.cache.DatabaseDriverFactory

class AppolloProvider(databaseDriverFactory: DatabaseDriverFactory) : TokenProvider {
    internal val database = Database(databaseDriverFactory)
    internal val apolloClient: ApolloClient = ApolloClient(
        networkTransport = ApolloHttpNetworkTransport(
            serverUrl = "https://ktor-graphql-server-4.herokuapp.com",
            headers = mapOf(
                "Accept" to "application/json",
                "Content-Type" to "application/json"
            ),
        ),
        interceptors = listOf(BearerTokenInterceptor(this))
    )

    override suspend fun currentToken(): String {
        return database.getUserState()?.token ?: ""
    }

    override suspend fun refreshToken(previousToken: String): String {
        return ""
    }
}
