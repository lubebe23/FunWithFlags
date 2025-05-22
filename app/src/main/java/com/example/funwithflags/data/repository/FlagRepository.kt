package com.example.funwithflags.data.repository.FlagRepository
import com.example.funwithflags.data.FlagDao
import com.example.funwithflags.data.FlagEntity
import kotlinx.coroutines.flow.Flow

class FlagRepository(private val flagDao: FlagDao) {
    val allFlags: Flow<List<FlagEntity>> = flagDao.getAllFlags()
    val favoriteFlags: Flow<List<FlagEntity>> = flagDao.getFavoriteFlags()

    suspend fun getFlagById(id: Int): FlagEntity? = flagDao.getFlagById(id)

    suspend fun insertFlags(flags: List<FlagEntity>) {
        flagDao.insertFlags(flags)
    }


    suspend fun insertFlag(flag: FlagEntity) {
        flagDao.insertFlag(flag)
    }

    suspend fun toggleFavorite(flag: FlagEntity) {
        flagDao.updateFlag(flag.copy(isFavorite = !flag.isFavorite))
    }

    fun getFlagsByFavoriteStatus(isFavorite: Boolean): Flow<List<FlagEntity>> {
        return flagDao.getFlagsByFavoriteStatus(isFavorite)
    }
}