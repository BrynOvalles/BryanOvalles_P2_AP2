package edu.ucne.bryanovalles_p2_ap2.data.local.remote

import edu.ucne.bryanovalles_p2_ap2.data.local.remote.dto.DepositoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.POST
import retrofit2.http.PUT

interface DepositoApi {
    @Headers("X-API-Key:test")
    @GET("api/Depositos")
    suspend fun getDepositos(): List<DepositoDto>

    @Headers("X-API-Key:test")
    @POST("api/Depositos")
    suspend fun saveDeposito(@Body depositoDto: DepositoDto?): DepositoDto

    @Headers("X-API-Key:test")
    @PUT("api/Depositos/{id}")
    suspend fun updateDeposito(
        @Path("id") id: Int,
        @Body deposito: DepositoDto
    ): Response<DepositoDto>

    @Headers("X-API-Key:test")
    @DELETE("api/Depositos/{id}")
    suspend fun deleteDeposito(@Path("id") id: Int): Response<Unit>
}