package com.rkd.simlabor.network

import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val username: String, val password: String)
data class LoginResponse(val token: String?, val status: Int)
data class TokenValidationResponse(val status: Int)

interface ApiService {
    @POST("api/login/username")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("api/login/token")
    suspend fun validateToken(@Body token: Map<String, String>): TokenValidationResponse
}

