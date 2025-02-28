package edu.ucne.bryanovalles_p2_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.bryanovalles_p2_ap2.data.local.entity.DepositoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DepositoDao {
    @Upsert()
    suspend fun save(depositoEntity: List<DepositoEntity>)

    @Query(
        """
            SELECT *
            FROM "deposito"
            WHERE idDeposito=:id
            LIMIT 1
        """
    )
    suspend fun find(id: Int): DepositoEntity?

    @Delete
    suspend fun delete(depositoEntity: DepositoEntity)

    @Query("SELECT * FROM Deposito")
    fun getAll(): Flow<List<DepositoEntity>>
}