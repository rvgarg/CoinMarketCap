package com.example.coinmarketcapclone

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
class Currencies(@PrimaryKey @ColumnInfo(name = "currency_id") val CurrencyId: String, @ColumnInfo(name = "is_selected") var isSelected: Boolean = false) {
    init {
        if (CurrencyId.toLowerCase() == "usd")
            isSelected = true
    }
}