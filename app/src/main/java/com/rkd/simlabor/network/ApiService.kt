package com.rkd.simlabor.network


import com.rkd.simlabor.data.DataClassUser
import com.rkd.simlabor.data.DataResponseAset
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

data class LoginRequest(
    val username: String,
    val password: String,
)
data class LoginResponse(
    val token: String?,
    val status: Int
)
data class TokenValidationResponse(
    val status: Int,
    val user: DataClassUser?
)
data class DataResponse(
    val message: String
)

interface ApiService {
    @POST("api/login/username")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("api/login/token")
    suspend fun validateToken(@Body token: Map<String, String>): TokenValidationResponse

    @GET("api/data")
    suspend fun getAset(
        @Header("Authorization") token: String,
        @Query("keyword") keyword: String,
        @Query("page") pages: Int = 1,
    ): DataResponseAset
}

