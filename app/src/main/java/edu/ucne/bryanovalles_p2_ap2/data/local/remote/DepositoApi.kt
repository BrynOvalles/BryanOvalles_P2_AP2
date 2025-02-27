package edu.ucne.bryanovalles_p2_ap2.data.local.remote

import edu.ucne.bryanovalles_p2_ap2.data.local.remote.dto.DepositoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.POST
import retrofit2.http.PUT

interface DepositoApi {
    @GET("api/Depositos")
    suspend fun getDeposito(): List<DepositoDto>

    @GET("api/Depositos/{id}")
    suspend fun getDeposito(@Path("id") id: Int): DepositoDto

    @POST("api/Depositos")
    suspend fun saveDeposito(@Body entidadDto: DepositoDto?): DepositoDto

    @PUT("api/Depositos/{id}")
    suspend fun updateDeposito(
        @Path("id") id: Int,
        @Body entidad: DepositoDto
    ): Response<DepositoDto>

    @DELETE("api/Depositos/{id}")
    suspend fun deleteDeposito(@Path("id") id: Int): Response<Unit>
}