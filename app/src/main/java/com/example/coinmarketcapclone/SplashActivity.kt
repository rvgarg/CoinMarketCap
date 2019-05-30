package com.example.coinmarketcapclone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {

    var selectedCurrencies: Currencies = Currencies(CurrencyId = "usd")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val apiInerface: ApiInterface = ApiClient.getClient()!!.create(ApiInterface::class.java)
        apiInerface.getCurrencies().enqueue(object : Callback<List<String>> {
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.e("Currency failed", t.message)

            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                doAsync {
                    CurrenciesWork(response)
                }
            }
        })
        apiInerface.getCoins(currency = selectedCurrencies.CurrencyId).enqueue(object : Callback<List<Coins>> {
            override fun onFailure(call: Call<List<Coins>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Coins>>, response: Response<List<Coins>>) {
                doAsync {
                    CoinsWork(response)
                }
            }
        })

        doAsync {
            if (CoinsViewModel(application).getHoldingsNumber() == 0)
                runOnUiThread { startActivity(Intent(this@SplashActivity, ScrollingActivity::class.java)) }
            else
                runOnUiThread { startActivity(Intent(this@SplashActivity, HoldingsActivity::class.java)) }
        }

    }


    fun CoinsWork(response: Response<List<Coins>>) {
        Log.e("coins", "retrived" + response.body().toString())
        val coinsViewModel = CoinsViewModel(application)
        if (coinsViewModel.getCoinsCount() == 0) {
            Log.e("inserting", "coins")
            response.body()!!.forEach {
                coinsViewModel.Insert(it)
            }
        } else
            response.body()!!.forEach {
                coinsViewModel.updatePrice(coinId = it.coinId, price = it.coinPrice)
                coinsViewModel.updatePriceChange(coinId = it.coinId, change = it.priceChange)
            }
    }

    fun CurrenciesWork(response: Response<List<String>>) {
        val currenciesViewModel = CurrenciesViewModel(application)
        Log.e("currencies", "retrived" + response.body().toString())
        if (currenciesViewModel.getCurrenciesCount() == 0) {
            response.body()!!.forEach {
                val currencies = Currencies(it)
                currenciesViewModel.Insert(currencies = currencies)
            }
            Log.e("inserting", "currencies")
        } else
            selectedCurrencies = currenciesViewModel.getSelectedCurrency()
    }
}
