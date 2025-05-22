package com.example.funwithflags.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [FlagEntity::class],
    version = 1,
    exportSchema = true
)
abstract class FlagDatabase : RoomDatabase() {
    abstract fun flagDao(): FlagDao

    companion object {
        @Volatile
        private var INSTANCE: FlagDatabase? = null

        fun getDatabase(context: Context): FlagDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FlagDatabase::class.java,
                    "flags.db"
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                val dao = getDatabase(context).flagDao()

                                dao.insertFlags(FlagEntity.DEFAULT_FLAGS)
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}