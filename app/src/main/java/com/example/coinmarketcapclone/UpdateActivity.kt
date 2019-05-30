package com.example.coinmarketcapclone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_update.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onComplete
import android.text.Editable as Editable1

class UpdateActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        val intent = intent
        var coinHoldings = intent.getDoubleExtra("CoinHoldings", 0.0)
        val coinPrice = intent.getDoubleExtra("CoinPrice", 0.0)
        val coinId = intent.getStringExtra("CoinId")
        coinAmount.setText(coinHoldings.toString())
        totalAmount.text = "$${coinPrice * coinHoldings}"
        coinAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: android.text.Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                totalAmount.text = "$${coinPrice * s.toString().toDouble()}"
            }
        })
        add.setOnClickListener {
            coinAmount.setText((coinAmount.text.toString().toDouble() + 1.0).toString())
        }
        sub.setOnClickListener {
            val ans = coinAmount.text.toString().toDouble() - 1.0
            if (ans >= 0.0)
                coinAmount.setText(ans.toString())
            else
                coinAmount.setText(0.0.toString())
        }
        submit.setOnClickListener {
            coinHoldings = coinAmount.text.toString().toDouble()
            doAsync {
                CoinsViewModel(application).updateHoldings(coinId = coinId, holdings = coinHoldings)
                onComplete {
                    startActivity(Intent(this@UpdateActivity, SplashActivity::class.java))
                    finish()
                }
            }
        }
        remove.setOnClickListener {
            doAsync {
                CoinsViewModel(application).updateHoldings(coinId = coinId, holdings = 0.0)
                onComplete {
                    startActivity(Intent(this@UpdateActivity, SplashActivity::class.java))
                    finish()
                }
            }


        }
    }
}
