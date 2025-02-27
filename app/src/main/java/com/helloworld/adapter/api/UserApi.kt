package com.helloworld.adapter.api


import com.helloworld.adapter.networking.data_mapping.UserResponseDTO
import com.helloworld.adapter.repositories.TokenResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface IUserApi {

    @GET("/api?results=1000")
    suspend fun getUsers(): UserResponseDTO

    @POST("/refresh-token")
    @Headers("refresh: true")
    suspend fun getToken(): TokenResponse

    @POST("/upload")
    suspend fun uploadImage(@Body body: RequestBody)
}