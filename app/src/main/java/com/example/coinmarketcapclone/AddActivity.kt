package com.example.coinmarketcapclone

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import org.jetbrains.anko.doAsync

class AddActivity : AppCompatActivity() {
    private lateinit var submit: MaterialButton
    private lateinit var coinAmaount: TextInputEditText
    private lateinit var totalAmount: TextView
    private lateinit var close: ImageView
    private lateinit var logo: ImageView
    private lateinit var coinName: TextView
    private lateinit var coinId: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        submit = findViewById(R.id.submit)
        coinAmaount = findViewById(R.id.coinAmount)
        totalAmount = findViewById(R.id.totalAmount)
        close = findViewById(R.id.back)
        logo = findViewById(R.id.logo)
        coinId = findViewById(R.id.id)
        coinName = findViewById(R.id.name)
        val oldIntent = intent
        var LogoUrl = oldIntent.getStringExtra("LogoUrl")
        val CoinId = oldIntent.getStringExtra("CoinId")
        val CoinName = oldIntent.getStringExtra("CoinName")
        val CoinSymbol = oldIntent.getStringExtra("CoinSymbol")
        val CoinPrice = oldIntent.getDoubleExtra("CoinPrice", 0.0)
        var CoinHoldings = oldIntent.getDoubleExtra("CoinHoldings", 0.0)
        submit.isEnabled = false
        totalAmount.text = 0.0.toString()
        coinAmaount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var total = if (s!!.isEmpty())
                    0.0
                else
                    (s.toString().toDouble() * CoinPrice)
                totalAmount.text = "$$total"
                submit.isEnabled = !s.isEmpty()
            }
        })
        Glide.with(this).load(LogoUrl).centerCrop().into(logo)
        coinName.text = CoinName
        coinId.text = CoinSymbol
        submit.setOnClickListener {
            doAsync {
                val coinViewModel = CoinsViewModel(application)
                coinViewModel.updateHoldings(coinId = CoinId, holdings = coinAmaount.text.toString().toDouble())
                runOnUiThread {
                    startActivity(Intent(this@AddActivity, HoldingsActivity::class.java))
                    finish()
                }
            }
        }
    }
}
