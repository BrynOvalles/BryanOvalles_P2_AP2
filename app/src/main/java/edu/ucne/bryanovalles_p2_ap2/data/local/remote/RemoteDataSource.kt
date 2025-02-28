package edu.ucne.bryanovalles_p2_ap2.data.local.remote

import edu.ucne.bryanovalles_p2_ap2.data.local.remote.dto.DepositoDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val depositoApi: DepositoApi
) {
    suspend fun getDepositos() = depositoApi.getDepositos()
    suspend fun getDeposito(id: Int) = depositoApi.getDeposito(id)
    suspend fun saveDeposito(depositoDto: DepositoDto) = depositoApi.saveDeposito(depositoDto)
    suspend fun updateDeposito(id: Int, depositoDto: DepositoDto) = depositoApi.updateDeposito(id, depositoDto)
    suspend fun deleteDeposito(id: Int) = depositoApi.deleteDeposito(id)
}