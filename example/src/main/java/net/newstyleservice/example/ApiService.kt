package net.newstyleservice.example

import retrofit2.http.GET

interface ApiService {
    @GET("/shikure_table.json")
    suspend fun getShikure(): MutableList<Shikure>?
}
