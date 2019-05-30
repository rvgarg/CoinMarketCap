package com.example.coinmarketcapclone

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CurrenciesDao {
    @Insert
    fun Insert(currencies: Currencies)

    @Query("SELECT * FROM currencies WHERE is_selected = 1")
    fun getSelectedCurrency(): Currencies

    @Query(value = "SELECT * FROM currencies WHERE is_selected = 1")
    fun getUnselctedCurrencies(): List<Currencies>

    @Query(value = "DELETE FROM currencies")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM currencies")
    fun getCurrenciesCount(): Int
}