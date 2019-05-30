package com.example.coinmarketcapclone

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CoinsDao {
    @Insert
    fun insert(coins: Coins)

    @Query(value = "SELECT * FROM coins WHERE coin_holdings>0")
    fun getAllHoldingsData(): List<Coins>

    @Query(value = "Select COUNT(*) FROM coins WHERE coin_holdings>0")
    fun getHoldingsNumber(): Int

    @Query("UPDATE coins SET coin_holdings = :holdings WHERE coin_id = :coin_id")
    fun updateHoldings(coin_id: String, holdings: Double)

    @Query("UPDATE coins SET coin_price = :price WHERE coin_id = :coin_id")
    fun updatePrice(coin_id: String, price: Double)

    @Query(value = "SELECT * FROM coins ORDER BY coin_holdings DESC")
    fun getAllCoins(): List<Coins>

    @Query("SELECT COUNT(*) FROM coins ORDER BY coin_holdings DESC")
    fun getCoinsCount(): Int

    @Query("UPDATE coins SET `24_hr_price_change` = :change WHERE coin_id = :coin_id")
    fun updatePriceChange(coin_id: String, change: Double)

    @Query("SELECT * FROM coins WHERE coin_holdings = 0.0")
    fun getAllNonHoldings(): List<Coins>

}