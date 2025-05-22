// app/src/main/java/com/example/funwithflags/data/FlagDao.kt
package com.example.funwithflags.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FlagDao {
    @Query("SELECT * FROM flags")
    fun getAllFlags(): Flow<List<FlagEntity>>

    @Query("SELECT * FROM flags WHERE is_favorite = 1")
    fun getFavoriteFlags(): Flow<List<FlagEntity>>

    @Query("SELECT * FROM flags WHERE id = :id")
    suspend fun getFlagById(id: Int): FlagEntity?

    @Insert
    suspend fun insertFlag(flag: FlagEntity)

    @Insert
    suspend fun insertFlags(flags: List<FlagEntity>)

    @Update
    suspend fun updateFlag(flag: FlagEntity)

    @Query("SELECT * FROM flags WHERE is_favorite = :isFavorite")
    fun getFlagsByFavoriteStatus(isFavorite: Boolean): Flow<List<FlagEntity>>
}