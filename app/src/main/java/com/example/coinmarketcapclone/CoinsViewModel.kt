package com.example.coinmarketcapclone

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class CoinsViewModel(application: Application) : AndroidViewModel(application) {
    private val coinsRepository: CoinsRepository = CoinsRepository(application)

    fun Insert(coins: Coins) = coinsRepository.Insert(coins = coins)

    fun updatePrice(coinId: String, price: Double) = coinsRepository.updatePrice(coinId = coinId, price = price)

    fun updateHoldings(coinId: String, holdings: Double) = coinsRepository.updateHoldings(coinId, holdings)

    fun getHoldingsNumber() = coinsRepository.getHoldingsNumber()

    fun getAllHoldings() = coinsRepository.getAllHoldings()

    fun getAllCoins() = coinsRepository.getAllCoins()

    fun getCoinsCount() = coinsRepository.getCoinsCount()

    fun updatePriceChange(coinId: String, change: Double) =
        coinsRepository.updatePriceChange(coinId = coinId, change = change)

    fun getAllNonHoldings() = coinsRepository.getAllNonHoldings()
}