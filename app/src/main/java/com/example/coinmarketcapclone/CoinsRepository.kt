package com.example.coinmarketcapclone

import android.app.Application

class CoinsRepository(application: Application) {
    private var coinsDao: CoinsDao

    init {
        val db = CoinsDatabase.getDataBase(application)
        coinsDao = db!!.coinsDao()
    }

    fun Insert(coins: Coins) = coinsDao.insert(coins = coins)


    fun getAllHoldings() = coinsDao.getAllHoldingsData()


    fun getHoldingsNumber() = coinsDao.getHoldingsNumber()

    fun updateHoldings(coinId: String, holdings: Double) =
        coinsDao.updateHoldings(coin_id = coinId, holdings = holdings)

    fun updatePrice(coinId: String, price: Double) = coinsDao.updatePrice(coin_id = coinId, price = price)

    fun getAllCoins() = coinsDao.getAllCoins()

    fun getCoinsCount() = coinsDao.getCoinsCount()

    fun updatePriceChange(coinId: String, change: Double) =
        coinsDao.updatePriceChange(coin_id = coinId, change = change)

    fun getAllNonHoldings() = coinsDao.getAllNonHoldings()
}