package com.example.coinmarketcapclone

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("coins/markets")
    fun getCoins(@Query(value = "vs_currency") currency: String, @Query(value = "order") order: String = "order=market_cap_desc"): Call<List<Coins>>

    @GET(value = "simple/supported_vs_currencies")
    fun getCurrencies():Call<List<String>>
}