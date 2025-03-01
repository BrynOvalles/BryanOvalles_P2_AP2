package edu.ucne.bryanovalles_p2_ap2.data.repository

import android.util.Log
import edu.ucne.bryanovalles_p2_ap2.data.local.dao.DepositoDao
import edu.ucne.bryanovalles_p2_ap2.data.local.entity.DepositoEntity
import edu.ucne.bryanovalles_p2_ap2.data.local.remote.RemoteDataSource
import edu.ucne.bryanovalles_p2_ap2.data.local.remote.Resource
import edu.ucne.bryanovalles_p2_ap2.data.local.remote.dto.DepositoDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class DepositoRepository @Inject constructor(
    private val depositoDao: DepositoDao,
    private val remoteDataSource: RemoteDataSource,
) {
    fun getDepositos(): Flow<Resource<List<DepositoEntity>>> = flow {
        emit(Resource.Loading())
        try {
            val depositoRemoto = remoteDataSource.getDepositos()

            val listaDeposito = depositoRemoto.map { dto ->
                DepositoEntity(
                    idDeposito = dto.idDeposito,
                    fecha = dto.fecha,
                    idCuenta = dto.idCuenta,
                    concepto = dto.concepto,
                    monto = dto.monto
                )
            }
            depositoDao.save(listaDeposito)
            emit(Resource.Success(listaDeposito))
        } catch (e: HttpException) {
            val errorMessage = e.response()?.errorBody()?.string() ?: e.message()
            emit(Resource.Error("Error de conexión $errorMessage"))
        } catch (e: Exception) {

            val localDeposito = depositoDao.getAll().first()
            if (localDeposito.isNotEmpty())
                emit(Resource.Success(localDeposito))
            else
                emit(Resource.Error("Error de conexión: ${e.message}"))
        }
    }

    suspend fun find(id: Int): DepositoEntity? {
        val depositosDto = remoteDataSource.getDepositos()
        return depositosDto
            .firstOrNull { it.idDeposito == id }
            ?.let { depositoDto ->
                DepositoEntity(
                    idDeposito = depositoDto.idDeposito,
                    fecha = depositoDto.fecha,
                    idCuenta = depositoDto.idCuenta,
                    concepto = depositoDto.concepto,
                    monto = depositoDto.monto
                )
            }
    }
    suspend fun update(id: Int, depositoDto: DepositoDto) = remoteDataSource.updateDeposito(id, depositoDto)
    suspend fun save(depositoDto: DepositoDto) = remoteDataSource.saveDeposito(depositoDto)
    suspend fun delete(id: Int) = remoteDataSource.deleteDeposito(id)
}