package com.example.coinmarketcapclone

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coins")
class Coins(
    @PrimaryKey
    @ColumnInfo(name = "coin_id")
    @SerializedName("id")
    val coinId: String,

    @ColumnInfo(name = "coin_name")
    @SerializedName("name")
    val coinName: String,

    @ColumnInfo(name = "coin_symbol")
    @SerializedName("symbol")
    val coinSymbol: String,

    @ColumnInfo(name = "coin_price")
    @SerializedName("current_price")
    var coinPrice: Double,

    @ColumnInfo(name = "coin_holdings")
    var coinHoldings: Double = 0.0,

    @ColumnInfo(name = "logo_url")
    @SerializedName("image")
    val logoUrl: String,

    @SerializedName(value = "price_change_24h")
    @ColumnInfo(name = "24_hr_price_change")
    var priceChange: Double
)
