package com.example.coinmarketcapclone

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onComplete

class HoldingsActivity : AppCompatActivity() {

    lateinit var holdingList: List<Coins>
    lateinit var adapter: HoldingsAdapter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.present_layout)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val add = findViewById<AppCompatImageButton>(R.id.add)
        val totalHoldings = findViewById<TextView>(R.id.totalHoldings)
        val changeText = findViewById<TextView>(R.id.change)
        doAsync {
            val coinsViewModel = CoinsViewModel(application)
            holdingList = coinsViewModel.getAllHoldings()
            onComplete {
                recyclerView.layoutManager = LinearLayoutManager(this@HoldingsActivity)
                recyclerView.itemAnimator = DefaultItemAnimator()
                adapter = HoldingsAdapter(context = this@HoldingsActivity, list = holdingList)
                recyclerView.adapter = adapter
                var total = 0.0
                var change = 0.0
                holdingList.forEach {
                    change += (it.coinHoldings * it.priceChange)
                    total += (it.coinPrice * it.coinHoldings)
                }
                totalHoldings.text = "$${String.format("%.2f", total)}"
                changeText.text = "$${String.format("%.2f", change)}"
                changeText.setTextColor(Color.WHITE)
                if (change < 0.0)
                    changeText.setBackgroundColor(Color.RED)
                else
                    changeText.setBackgroundColor(Color.GREEN)
            }
        }

        add.setOnClickListener {
            val intent = Intent(this, AddListActivity::class.java)
            startActivity(intent)
        }

    }
}
