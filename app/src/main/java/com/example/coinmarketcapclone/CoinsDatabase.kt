package com.example.coinmarketcapclone

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Coins::class, Currencies::class], version = 1)
abstract class CoinsDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: CoinsDatabase? = null

        fun getDataBase(context: Context): CoinsDatabase? {
            if (INSTANCE == null) {
                synchronized(CoinsDatabase::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder<CoinsDatabase>(
                            context.applicationContext,
                            CoinsDatabase::class.java,
                            "coin_database"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }

    abstract fun coinsDao(): CoinsDao
    abstract fun currenciesDao() : CurrenciesDao
}