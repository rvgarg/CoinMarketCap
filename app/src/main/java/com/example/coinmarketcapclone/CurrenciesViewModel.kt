package com.example.coinmarketcapclone

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class CurrenciesViewModel(application: Application) : AndroidViewModel(application) {

    private val currenciesRepository: CurrenciesRepository = CurrenciesRepository(application)

    fun Insert(currencies: Currencies) = currenciesRepository.Insert(currencies = currencies)

    fun getSelectedCurrency() = currenciesRepository.getSelectedCurrency()

    fun getUnselectedCurrencies() = currenciesRepository.getUnselectedCurrencies()

    fun deleteAll() = currenciesRepository.deleteAll()

    fun getCurrenciesCount() = currenciesRepository.getCurrenciesCount()

}