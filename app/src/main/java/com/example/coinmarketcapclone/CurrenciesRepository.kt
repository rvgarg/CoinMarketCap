package com.example.coinmarketcapclone

import android.app.Application

class CurrenciesRepository(application: Application) {
    private val currenciesDao: CurrenciesDao

    init {
        val db = CoinsDatabase.getDataBase(application)
        currenciesDao = db!!.currenciesDao()
    }

    fun Insert(currencies: Currencies) = currenciesDao.Insert(currencies = currencies)

    fun getSelectedCurrency() = currenciesDao.getSelectedCurrency()

    fun getUnselectedCurrencies() = currenciesDao.getUnselctedCurrencies()

    fun deleteAll() = currenciesDao.deleteAll()

    fun getCurrenciesCount() = currenciesDao.getCurrenciesCount()
}